package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.AuthOtpLoginDTO;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.vo.TokenInfoVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 发送验证码
     */
    @PostMapping("/send-otp")
    public Result<Void> sendOtp() {
        return Result.ok();
    }

    /**
     * 验证码登录
     */
    @PostMapping("/otp-login")
    public Result<TokenInfoVO> otpLogin(@RequestBody @Validated AuthOtpLoginDTO dto) {
        if (!"888888".equals(dto.getOtp())) {
            return Result.error("验证码错误");
        }
        User user = userService.getUserByPhone(dto.getPhone());
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (user.getStatus() != UserStatusEnum.CONFIRMED) {
            return Result.error("用户状态异常");
        }
        StpUtil.checkDisable(user.getId());
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        TokenInfoVO tokenInfoVO = BeanUtil.copyProperties(tokenInfo, TokenInfoVO.class);
        return Result.data(tokenInfoVO);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.ok();
    }
}
