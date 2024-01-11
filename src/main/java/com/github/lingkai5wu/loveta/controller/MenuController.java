package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 当前登录用户菜单
     */
    @GetMapping("/current")
    public SaResult listCurrentUserMenus() {
        if (StpUtil.hasRole("super-admin")) {
            return listMenus();
        }
        long loginId = StpUtil.getLoginIdAsLong();
        List<Menu> menuList = menuService.listMenuByUserId(loginId);
        return SaResult.data(menuList);
    }

    @GetMapping
    @SaCheckPermission("data:menu:list")
    public SaResult listMenus() {
        List<Menu> menuList = menuService.list();
        return SaResult.data(menuList);
    }

    @PostMapping
    @SaCheckPermission("data:menu:save")
    public SaResult saveMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return SaResult.ok();
    }

    @PatchMapping
    @SaCheckPermission("data:menu:update")
    public SaResult updateMenu(@RequestBody Menu menu) {
        boolean updated = menuService.updateById(menu);
        if (!updated) {
            return SaResult.error("菜单不存在");
        }
        return SaResult.ok();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("data:menu:remove")
    public SaResult removeMenu(@PathVariable Long id) {
        boolean removed = menuService.removeById(id);
        if (!removed) {
            return SaResult.error("菜单不存在");
        }
        return SaResult.ok();
    }
}
