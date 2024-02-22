package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.query.AuthLoginQuery;
import com.github.lingkai5wu.loveta.model.vo.TokenInfoVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 登录
     */
    @PostMapping("/login")
    public Result<TokenInfoVO> login(@RequestBody @Validated AuthLoginQuery query) {
        if (!"888888".equals(query.getSmsCode())) {
            return Result.error("验证码错误");
        }
        User user = userService.getUserByPhone(query.getPhone());
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (user.getStatus() != 0) {
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
    @GetMapping("/logout")
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.ok();
    }
}
