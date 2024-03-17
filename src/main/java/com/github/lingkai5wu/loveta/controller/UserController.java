package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.UserSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.UserUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.User;
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
@RequestMapping("/users")
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
        return getUserVO(id);
    }

    /**
     * 获取用户
     */
    @GetMapping("/{id}")
    @SaCheckPermission("user:get")
    public Result<UserVO> getUserVO(@PathVariable long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        return Result.data(userVO);
    }

    /**
     * 列出全部用户
     */
    @GetMapping()
    @SaCheckPermission("user:list")
    public Result<List<UserVO>> listUserVOs() {
        List<User> userList = userService.list();
        List<UserVO> userVOList = BeanUtil.copyToList(userList, UserVO.class);
        return Result.data(userVOList);
    }

    /**
     * 新增用户
     */
    @PostMapping
    @SaCheckPermission("user:save")
    public Result<Void> saveUser(@RequestBody @Validated UserSaveDTO dto) {
        User user = BeanUtil.copyProperties(dto, User.class);
        userService.save(user);
        return Result.ok();
    }

    /**
     * 修改用户
     */
    @PutMapping
    @SaCheckPermission("user:update")
    public Result<Void> updateUser(@RequestBody @Validated UserUpdateDTO dto) {
        User user = BeanUtil.copyProperties(dto, User.class);
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
    @SaCheckPermission("user:remove")
    public Result<Void> removeUser(@PathVariable long id) {
        boolean removed = userService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
