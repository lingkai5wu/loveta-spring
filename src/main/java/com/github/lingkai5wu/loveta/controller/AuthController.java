package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.query.UserAuthQuery;
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
    public Result<TokenInfoVO> login(@RequestBody @Validated UserAuthQuery query) {
        User user = userService.getUserByPhone(query.getPhone());
        if (user == null || !BCrypt.checkpw(query.getPassword(), user.getPassword())) {
            return Result.error("手机号或密码不正确");
        }
        if (user.getStatus() != 0) {
            return Result.error("用户状态异常");
        }
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        TokenInfoVO tokenInfoVO = BeanUtil.copyProperties(tokenInfo, TokenInfoVO.class);
        return Result.data(tokenInfoVO);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Validated UserAuthQuery query) {
        if (userService.getUserByPhone(query.getPhone()) != null) {
            return Result.error("该号码已注册");
        }
        User user = new User().setPhone(query.getPhone()).setPassword(BCrypt.hashpw(query.getPassword(), BCrypt.gensalt()));
        userService.save(user);
        return Result.ok();
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
