package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.query.UserSaveQuery;
import com.github.lingkai5wu.loveta.model.query.UserUpdateQuery;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result<UserVO> getCurrentUserVO() {
        long id = StpUtil.getLoginIdAsLong();
        UserVO userVO = userService.getUserVOById(id);
        return Result.data(userVO);
    }

    /**
     * 列出全部用户
     */
    @GetMapping()
    @SaCheckPermission("data:user:list")
    public Result<List<UserVO>> listUserVOs() {
        List<UserVO> userVOList = userService.listUserVOs();
        return Result.data(userVOList);
    }

    /**
     * 新增用户
     */
    @PostMapping
    @SaCheckPermission("data:user:save")
    public Result<Void> saveUser(@RequestBody @Validated UserSaveQuery saveQuery) {
        User user = BeanUtil.copyProperties(saveQuery, User.class);
        userService.save(user);
        return Result.ok();
    }

    /**
     * 修改用户
     */
    @PatchMapping
    @SaCheckPermission("data:user:update")
    public Result<Void> updateUser(@RequestBody @Validated UserUpdateQuery updateQuery) {
        User user = BeanUtil.copyProperties(updateQuery, User.class);
        boolean updated = userService.updateById(user);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("data:user:remove")
    public Result<Void> removeUser(@PathVariable Long id) {
        boolean removed = userService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
