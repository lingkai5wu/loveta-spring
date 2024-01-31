package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 获取当前用户
     */
    @GetMapping("/current")
    public Result getCurrentUserVO() {
        long id = StpUtil.getLoginIdAsLong();
        UserVO userVO = userService.getUserVOById(id);
        return Result.data(userVO);
    }

    /**
     * 列出全部用户
     */
    @GetMapping()
    @SaCheckPermission("data:user:list")
    public Result listUserVOs() {
        List<UserVO> userVOList = userService.listUserVOs();
        return Result.data(userVOList);
    }
}
