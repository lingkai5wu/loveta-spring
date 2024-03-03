package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.model.query.MenuSaveQuery;
import com.github.lingkai5wu.loveta.model.query.MenuUpdateQuery;
import com.github.lingkai5wu.loveta.model.vo.MenuVO;
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
    public Result<List<MenuVO>> listCurrentUserMenuVOs() {
        long id = StpUtil.getLoginIdAsLong();
        List<MenuVO> menuVOList = BeanUtil.copyToList(menuService.listMenusByUserId(id), MenuVO.class);
        return Result.data(menuVOList);
    }

    /**
     * 列出全部菜单
     */
    @GetMapping
    @SaCheckPermission("data:menu:list")
    public Result<List<MenuVO>> listMenus() {
        List<MenuVO> menuVOList = BeanUtil.copyToList(menuService.list(), MenuVO.class);
        return Result.data(menuVOList);
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @SaCheckPermission("data:menu:save")
    public Result<Void> saveMenu(@RequestBody MenuSaveQuery saveQuery) {
        Menu menu = BeanUtil.copyProperties(saveQuery, Menu.class);
        menuService.save(menu);
        return Result.ok();
    }

    /**
     * 修改菜单
     */
    @PatchMapping
    @SaCheckPermission("data:menu:update")
    public Result<Void> updateMenu(@RequestBody MenuUpdateQuery updateQuery) {
        Menu menu = BeanUtil.copyProperties(updateQuery, Menu.class);
        boolean updated = menuService.updateById(menu);
        if (!updated) {
            return Result.status(ResultStatusEnum.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("data:menu:remove")
    public Result<Void> removeMenu(@PathVariable Long id) {
        boolean removed = menuService.removeById(id);
        if (!removed) {
            return Result.status(ResultStatusEnum.NOT_FOUND);
        }
        return Result.ok();
    }
}
