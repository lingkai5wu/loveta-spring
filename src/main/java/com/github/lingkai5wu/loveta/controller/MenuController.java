package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 列出当前用户菜单
     */
    @GetMapping("/current")
    public Result listCurrentUserMenus() {
        if (StpUtil.hasRole("super-admin")) {
            return listMenus();
        }
        long loginId = StpUtil.getLoginIdAsLong();
        List<Menu> menuList = menuService.listMenuByUserId(loginId);
        return Result.data(menuList);
    }

    /**
     * 列出全部菜单
     */
    @GetMapping
    @SaCheckPermission("data:menu:list")
    public Result listMenus() {
        List<Menu> menuList = menuService.list();
        return Result.data(menuList);
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @SaCheckPermission("data:menu:save")
    public Result saveMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.ok();
    }

    /**
     * 修改菜单
     */
    @PatchMapping
    @SaCheckPermission("data:menu:update")
    public Result updateMenu(@RequestBody Menu menu) {
        boolean updated = menuService.updateById(menu);
        if (!updated) {
            return Result.error("菜单不存在");
        }
        return Result.ok();
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("data:menu:remove")
    public Result removeMenu(@PathVariable Long id) {
        boolean removed = menuService.removeById(id);
        if (!removed) {
            return Result.error("菜单不存在");
        }
        return Result.ok();
    }
}
