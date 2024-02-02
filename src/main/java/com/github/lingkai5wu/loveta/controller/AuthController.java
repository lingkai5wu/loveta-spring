package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.query.AuthLoginQuery;
import com.github.lingkai5wu.loveta.model.query.AuthRegisterQuery;
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
        User user = userService.getUserByPhone(query.getPhone());
        if (user == null || !BCrypt.checkpw(query.getPassword(), user.getPassword())) {
            return Result.error("手机号或密码不正确");
        }
        if (user.getStatus() != 0) {
            return Result.error("用户状态异常");
        }
        TokenInfoVO tokenInfoVO = loginAndGetTokenInfoVO(user);
        return Result.data(tokenInfoVO);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<TokenInfoVO> register(@RequestBody @Validated AuthRegisterQuery query) {
        // TODO 验证码
        if (!query.getSmsCode().equals("808080")) {
            return Result.error("验证码错误");
        }

        if (userService.getUserByPhone(query.getPhone()) != null) {
            return Result.error("该号码已注册");
        }
        User user = new User().setPhone(query.getPhone()).setPassword(BCrypt.hashpw(query.getPassword(), BCrypt.gensalt()));
        userService.save(user);
        // TODO 将用户加入默认用户组
        TokenInfoVO tokenInfoVO = loginAndGetTokenInfoVO(user);
        return Result.data(tokenInfoVO);
    }

    private static TokenInfoVO loginAndGetTokenInfoVO(User user) {
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return BeanUtil.copyProperties(tokenInfo, TokenInfoVO.class);
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
