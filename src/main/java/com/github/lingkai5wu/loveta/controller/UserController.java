package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public SaResult getUserVo() {
        long id = StpUtil.getLoginIdAsLong();
        UserVO userVo = userService.getUserVoById(id);
        return SaResult.data(userVo);
    }

    @GetMapping("/{id}")
    @SaCheckPermission("data:user:get")
    public SaResult getUserVoById(@PathVariable Long id) {
        UserVO userVo = userService.getUserVoById(id);
        return SaResult.data(userVo);
    }

    @GetMapping("/list")
    @SaCheckPermission("data:user:list")
    public SaResult listUserVOs() {
        List<UserVO> userVOList = userService.listUserVOs();
        return SaResult.data(userVOList);
    }
}
