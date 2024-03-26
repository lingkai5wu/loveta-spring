package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lingkai5wu.loveta.model.PageDTO;
import com.github.lingkai5wu.loveta.model.PageVO;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.PermissionUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.model.vo.PermissionVO;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限
 */
@RestController
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {

    private final IPermissionService permissionService;

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
        List<Permission> permissionList = permissionService.list();
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(permissionList, PermissionVO.class);
        return Result.data(permissionVOList);
    }

    /**
     * 分页列出全部权限
     */
    @GetMapping("/page")
    @SaCheckPermission("permission:list")
    public Result<PageVO<PermissionVO>> listPermissionVOsWithPage(PageDTO pageDTO) {
        Page<Permission> page = new Page<>();
        BeanUtil.copyProperties(pageDTO, page, new CopyOptions().ignoreNullValue());
        page = permissionService.page(page);
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(page.getRecords(), PermissionVO.class);
        return Result.page(permissionVOList, page);
    }

    /**
     * 同步权限
     */
    @PostMapping("/sync")
    @SaCheckPermission("permission:sync")
    public Result<Void> syncPermission() {
        Set<String> reflectionPermissionCodeSet = permissionService.getPermissionCodeSetFromReflection();
        reflectionPermissionCodeSet.add("*");
        Set<String> dbPermissionCodeSet = permissionService.lambdaQuery()
                .select(Permission::getCode)
                .list()
                .stream()
                .map(Permission::getCode)
                .collect(Collectors.toSet());

        List<String> inDbNotInReflection = CollUtil.subtractToList(dbPermissionCodeSet, reflectionPermissionCodeSet);
        List<String> inReflectionNotInDb = CollUtil.subtractToList(reflectionPermissionCodeSet, dbPermissionCodeSet);

        permissionService.batchDeletePermissionsByCode(inDbNotInReflection);
        List<Permission> permissionListToAdd = inReflectionNotInDb.stream()
                .map(code -> new Permission().setCode(code))
                .collect(Collectors.toList());
        permissionService.saveBatch(permissionListToAdd);
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
}
