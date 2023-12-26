package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Menu;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> listMenuByUserId(long id);
}
