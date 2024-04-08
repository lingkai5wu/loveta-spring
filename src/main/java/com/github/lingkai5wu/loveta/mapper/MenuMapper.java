package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Menu;

import java.util.List;

/**
 * 菜单 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listMenusByUserId(int id);

    List<Menu> listMenusByRoleId(int id);

    boolean batchInsertRoleMenus(int roleId, List<Integer> menuIdsToInsert);

    boolean batchDeleteRoleMenus(int roleId, List<Integer> menuIdsToDelete);
}
