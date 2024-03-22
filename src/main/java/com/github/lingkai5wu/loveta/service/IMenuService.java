package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.dto.BatchManyToManyDTO;
import com.github.lingkai5wu.loveta.model.po.Menu;

import java.util.List;

/**
 * 菜单 服务类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> listMenusByUserId(long id);

    boolean verifyParent(Menu menu);

    boolean childExistsById(int id);

    List<Menu> listMenusByRoleId(int id);

    void updateRoleMenuByBatch(int roleId, BatchManyToManyDTO dto);
}
