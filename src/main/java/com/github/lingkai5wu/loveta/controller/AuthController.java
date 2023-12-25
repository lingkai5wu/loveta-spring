package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.model.dto.UserAuthDTO;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 认证 前端控制器
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public SaResult login(@RequestBody UserAuthDTO dto) {
        User user = userService.getByPhone(dto.getPhone());
        if (user == null || !BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
            return SaResult.error("手机号或密码不正确");
        }
        if (user.getStatus() != 0) {
            return SaResult.error("用户状态异常");
        }
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return SaResult.data(tokenInfo);
    }

    @PostMapping("/register")
    public SaResult register(@RequestBody UserAuthDTO dto) {
        User user = userService.getByPhone(dto.getPhone());
        if (user != null) {
            return SaResult.error("手机号已注册");
        }
        user = new User();
        BeanUtils.copyProperties(dto, user);
        userService.save(user);
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return SaResult.data(tokenInfo);
    }
}
