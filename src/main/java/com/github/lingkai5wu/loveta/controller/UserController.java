package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public SaResult getUser() {
        long id = StpUtil.getLoginIdAsLong();
        User user = userService.getById(id);
        return SaResult.data(user);
    }

    @GetMapping("/{id}")
    @SaCheckPermission("user.get")
    public SaResult getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return SaResult.data(user);
    }
}
