package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.PermissionSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.PermissionUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.model.vo.PermissionVO;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final IPermissionService permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 列出当前用户权限
     */
    @GetMapping("/current")
    public Result<List<PermissionVO>> listCurrentUserPermissionVOs() {
        long id = StpUtil.getLoginIdAsLong();
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(permissionService.listPermissionsByUserId(id), PermissionVO.class);
        return Result.data(permissionVOList);
    }

    /**
     * 获取权限
     */
    @GetMapping("/{id}")
    @SaCheckPermission("permission:get")
    public Result<PermissionVO> getPermissionVO(@PathVariable int id) {
        Permission permission = permissionService.getById(id);
        if (permission == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        PermissionVO permissionVO = BeanUtil.copyProperties(permission, PermissionVO.class);
        return Result.data(permissionVO);
    }

    /**
     * 列出全部权限
     */
    @GetMapping
    @SaCheckPermission("permission:list")
    public Result<List<PermissionVO>> listPermissionVOs() {
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(permissionService.list(), PermissionVO.class);
        return Result.data(permissionVOList);
    }

    /**
     * 新增权限
     */
    @PostMapping
    @SaCheckPermission("permission:save")
    public Result<Void> savePermission(@RequestBody @Validated PermissionSaveDTO dto) {
        Permission permission = BeanUtil.copyProperties(dto, Permission.class);
        permissionService.save(permission);
        return Result.ok();
    }

    /**
     * 修改权限
     */
    @PutMapping
    @SaCheckPermission("permission:update")
    public Result<Void> updatePermission(@RequestBody @Validated PermissionUpdateDTO dto) {
        Permission permission = BeanUtil.copyProperties(dto, Permission.class);
        boolean updated = permissionService.updateById(permission);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("permission:remove")
    public Result<Void> removePermission(@PathVariable int id) {
        boolean removed = permissionService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
