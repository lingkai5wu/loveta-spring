package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Role;

import java.util.List;

/**
 * 角色 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> listRoleCodesByUserId(Object id);

    List<Role> listRolesByUserId(int id);
}
