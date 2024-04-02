package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.MenuOrderUpdateDTO;
import com.github.lingkai5wu.loveta.model.dto.MenuSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.MenuUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.model.query.MenuQuery;
import com.github.lingkai5wu.loveta.model.vo.MenuVO;
import com.github.lingkai5wu.loveta.service.IMenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@RestController
@RequestMapping("/menus")
@AllArgsConstructor
public class MenuController {

    private final IMenuService menuService;

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
     * 获取菜单
     */
    @GetMapping("/{id}")
    @SaCheckPermission("menu:get")
    public Result<MenuVO> getMenuVO(@PathVariable int id) {
        Menu menu = menuService.getById(id);
        if (menu == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MenuVO menuVO = BeanUtil.copyProperties(menu, MenuVO.class);
        return Result.data(menuVO);
    }

    /**
     * 列出全部菜单
     */
    @GetMapping
    @SaCheckPermission("menu:list")
    public Result<List<MenuVO>> listMenuVOs(MenuQuery query) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>(BeanUtil.copyProperties(query, Menu.class));
        List<MenuVO> menuVOList = BeanUtil.copyToList(menuService.list(wrapper), MenuVO.class);
        return Result.data(menuVOList);
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @SaCheckPermission("menu:save")
    public Result<Void> saveMenu(@RequestBody @Validated MenuSaveDTO dto) {
        Menu menu = BeanUtil.copyProperties(dto, Menu.class);
        if (!menuService.verifyParent(menu)) {
            return Result.error("父菜单无效");
        }
        menuService.save(menu);
        return Result.ok();
    }

    /**
     * 修改菜单
     */
    @PutMapping
    @SaCheckPermission("menu:update")
    public Result<Void> updateMenu(@RequestBody @Validated MenuUpdateDTO dto) {
        if (dto.getType() != null
                && dto.getType() != MenuTypeEnum.PARENT
                && menuService.childExistsById(dto.getId())) {
            return Result.error("存在子菜单");
        }
        Menu menu = BeanUtil.copyProperties(dto, Menu.class);
        if (!menuService.verifyParent(menu)) {
            return Result.error("父菜单无效");
        }
        boolean updated = menuService.updateById(menu);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 批量修改菜单排序
     */
    @PostMapping("/batch-update-order")
    @SaCheckPermission("menu:update")
    public Result<Void> batchUpdateMenuOrder(@RequestBody @Valid List<MenuOrderUpdateDTO> dtoList) {
        List<Menu> menuList = BeanUtil.copyToList(dtoList, Menu.class);
        for (Menu menu : menuList) {
            if (!menuService.verifyParent(menu)) {
                return Result.error("父菜单无效");
            }
        }
        menuService.updateBatchById(menuList);
        return Result.ok();
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("menu:remove")
    public Result<Void> removeMenu(@PathVariable int id) {
        if (menuService.childExistsById(id)) {
            return Result.error("存在子菜单");
        }
        boolean removed = menuService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
