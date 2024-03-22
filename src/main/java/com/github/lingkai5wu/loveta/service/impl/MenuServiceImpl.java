package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import com.github.lingkai5wu.loveta.mapper.MenuMapper;
import com.github.lingkai5wu.loveta.model.dto.BatchManyToManyDTO;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> listMenusByUserId(long id) {
        if (StpUtil.hasPermission("menu:list")) {
            return list();
        }
        return baseMapper.listMenusByUserId(id);
    }

    public boolean verifyParent(Menu menu) {
        // 根节点始终有效
        if (menu.getPid() == null || menu.getPid() == 0) {
            return true;
        }
        // 新增时认为只要父菜单存在，则有效
        if (menu.getId() == null) {
            Menu parentMenu = lambdaQuery()
                    .eq(Menu::getId, menu.getPid())
                    .one();
            return parentMenu.getType() == MenuTypeEnum.PARENT;
        }
        // 当前菜单与父菜单不能相同
        if (menu.getId().equals(menu.getPid())) {
            return false;
        }
        // 拿到剔除当前菜单的所有菜单 id 和 pid
        List<Menu> parentMenuList = lambdaQuery()
                .select(Menu::getId, Menu::getPid)
                .eq(Menu::getType, MenuTypeEnum.PARENT)
                .ne(Menu::getId, menu.getId())
                .list();
        // 当前正在检查的菜单ID
        int currentId = menu.getPid();
        // 开始追踪父菜单
        while (currentId != 0) {
            // 在菜单列表中查找当前菜单的父菜单
            boolean foundParent = false;
            for (Menu parentMenu : parentMenuList) {
                if (parentMenu.getId() == currentId) {
                    // 更新当前菜单为其父菜单
                    currentId = parentMenu.getPid();
                    foundParent = true;
                    break;
                }
            }
            // 如果没有找到父菜单，说明存在循环依赖
            if (!foundParent) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean childExistsById(int id) {
        return lambdaQuery()
                .eq(Menu::getPid, id)
                .exists();
    }

    @Override
    public List<Menu> listMenusByRoleId(int id) {
        return baseMapper.listMenusByRoleId(id);
    }

    @Override
    public void updateRoleMenuByBatch(int roleId, BatchManyToManyDTO dto) {
        if (dto.getTargetIdsToInsert() != null && !dto.getTargetIdsToInsert().isEmpty()) {
            baseMapper.batchInsertRoleMenus(roleId, dto.getTargetIdsToInsert());
        }
        if (dto.getTargetIdsToDelete() != null && !dto.getTargetIdsToDelete().isEmpty()) {
            baseMapper.batchDeleteRoleMenus(roleId, dto.getTargetIdsToDelete());
        }
    }
}
