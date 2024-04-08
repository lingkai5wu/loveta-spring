package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.BatchUpdateManyToManyDTO;
import com.github.lingkai5wu.loveta.model.dto.RoleSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.RoleUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.model.po.Role;
import com.github.lingkai5wu.loveta.model.vo.MenuVO;
import com.github.lingkai5wu.loveta.model.vo.PermissionVO;
import com.github.lingkai5wu.loveta.model.vo.RoleVO;
import com.github.lingkai5wu.loveta.service.IMenuService;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import com.github.lingkai5wu.loveta.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final IRoleService roleService;
    private final IPermissionService permissionService;
    private final IMenuService menuService;

    /**
     * 列出当前用户角色
     */
    @GetMapping("/current")
    public Result<List<RoleVO>> listCurrentUserRoleVOs() {
        int id = StpUtil.getLoginIdAsInt();
        List<RoleVO> roleVOList = BeanUtil.copyToList(roleService.listRolesByUserId(id), RoleVO.class);
        return Result.data(roleVOList);
    }

    /**
     * 获取角色
     */
    @GetMapping("/{id}")
    @SaCheckPermission("role:get")
    public Result<RoleVO> getRoleVO(@PathVariable int id) {
        Role role = roleService.getById(id);
        if (role == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        RoleVO roleVO = BeanUtil.copyProperties(role, RoleVO.class);
        return Result.data(roleVO);
    }

    /**
     * 列出角色权限
     */
    @GetMapping("/{id}/permissions")
    @SaCheckPermission({"role:get", "permission:list"})
    public Result<List<PermissionVO>> listRolePermissionVOs(@PathVariable int id) {
        boolean exists = roleService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<Permission> permissionList = permissionService.listPermissionsByRoleId(id);
        List<PermissionVO> permissionVOList = BeanUtil.copyToList(permissionList, PermissionVO.class);
        return Result.data(permissionVOList);
    }

    /**
     * 列出角色菜单
     */
    @GetMapping("/{id}/menus")
    @SaCheckPermission({"role:get", "menu:list"})
    public Result<List<MenuVO>> listRoleMenuVOs(@PathVariable int id) {
        boolean exists = roleService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<Menu> menuList = menuService.listMenusByRoleId(id);
        List<MenuVO> menuVOList = BeanUtil.copyToList(menuList, MenuVO.class);
        return Result.data(menuVOList);
    }

    /**
     * 列出全部角色
     */
    @GetMapping
    @SaCheckPermission("role:list")
    public Result<List<RoleVO>> listRoleVOs() {
        List<RoleVO> roleVOList = BeanUtil.copyToList(roleService.list(), RoleVO.class);
        return Result.data(roleVOList);
    }

    /**
     * 新增角色
     */
    @PostMapping
    @SaCheckPermission("role:save")
    public Result<Void> saveRole(@RequestBody @Validated RoleSaveDTO dto) {
        Role role = BeanUtil.copyProperties(dto, Role.class);
        roleService.save(role);
        return Result.ok();
    }

    /**
     * 修改角色
     */
    @PutMapping
    @SaCheckPermission("role:update")
    public Result<Void> updateRole(@RequestBody @Validated RoleUpdateDTO dto) {
        Role role = BeanUtil.copyProperties(dto, Role.class);
        boolean updated = roleService.updateById(role);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }


    /**
     * 修改角色权限
     */
    @PutMapping("/{id}/permissions")
    @SaCheckPermission({"role:update", "permission:update"})
    public Result<Void> batchUpdateRolePermission(@PathVariable int id, @RequestBody BatchUpdateManyToManyDTO<Integer> dto) {
        boolean exists = roleService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        permissionService.batchUpdateRolePermission(id, dto);
        return Result.ok();
    }

    /**
     * 修改角色菜单
     */
    @PutMapping("/{id}/menus")
    @SaCheckPermission({"role:update", "menu:update"})
    public Result<Void> batchUpdateRoleMenu(@PathVariable int id, @RequestBody BatchUpdateManyToManyDTO<Integer> dto) {
        boolean exists = roleService.existsById(id);
        if (!exists) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        menuService.batchUpdateRoleMenu(id, dto);
        return Result.ok();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("role:remove")
    public Result<Void> removeRole(@PathVariable int id) {
        boolean removed = roleService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
