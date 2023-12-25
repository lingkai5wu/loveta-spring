package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Menu;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getMenuByUserId(long id);
}
