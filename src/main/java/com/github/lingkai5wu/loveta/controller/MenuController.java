package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public SaResult listMenuByUserId() {
        if (StpUtil.hasRole("super-admin")) {
            return listMenu();
        }
        long loginId = StpUtil.getLoginIdAsLong();
        List<Menu> menu = menuService.listMenuByUserId(loginId);
        return SaResult.data(menu);
    }

    @GetMapping("/list")
    @SaCheckPermission("menu.list")
    public SaResult listMenu() {
        List<Menu> menu = menuService.list();
        return SaResult.data(menu);
    }
}
