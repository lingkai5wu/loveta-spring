package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.BatchManyToManyDTO;
import com.github.lingkai5wu.loveta.model.dto.UserSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.UserUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.model.po.Role;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.query.UserQuery;
import com.github.lingkai5wu.loveta.model.vo.PermissionVO;
import com.github.lingkai5wu.loveta.model.vo.RoleVO;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import com.github.lingkai5wu.loveta.service.IRoleService;
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
    private final IRoleService roleService;
    private final IPermissionService permissionService;

    public UserController(IUserService userService, IRoleService roleService, IPermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
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
     * 列出用户的角色
     */
    @GetMapping("/{id}/roles")
    @SaCheckPermission({"user:get", "role:list"})
    public Result<List<RoleVO>> listUserRoleVOs(@PathVariable long id) {
        boolean exists = userService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<Role> roleList = roleService.listRolesByUserId(id);
        List<RoleVO> roleVOList = BeanUtil.copyToList(roleList, RoleVO.class);
        return Result.data(roleVOList);
    }

    /**
     * 列出用户的权限
     */
    @GetMapping("/{id}/permissions")
    @SaCheckPermission({"user:get", "permission:list"})
    public Result<List<PermissionVO>> listUserPermissionVOs(@PathVariable long id) {
        boolean exists = userService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<Permission> permissionList = permissionService.listPermissionsByUserId(id);
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(permissionList, PermissionVO.class);
        return Result.data(permissionVOList);
    }

    /**
     * 列出全部用户
     */
    @GetMapping()
    @SaCheckPermission("user:list")
    public Result<List<UserVO>> listUserVOs(UserQuery query) {
        QueryWrapper<User> wrapper = new QueryWrapper<>(BeanUtil.copyProperties(query, User.class));
        List<UserVO> userVOList = BeanUtil.copyToList(userService.list(wrapper), UserVO.class);
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
     * 修改用户角色
     */
    @PutMapping("/{id}/roles")
    @SaCheckPermission({"user:update", "role:update"})
    public Result<Void> updateUserRoleByBatch(@PathVariable long id, @RequestBody BatchManyToManyDTO dto) {
        boolean exists = userService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        userService.updateUserRoleByBatch(id, dto);
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
