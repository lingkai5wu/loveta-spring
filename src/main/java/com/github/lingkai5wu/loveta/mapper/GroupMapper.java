package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Group;

import java.util.List;

/**
 * <p>
 * 用户组 Mapper 接口
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface GroupMapper extends BaseMapper<Group> {

    List<String> listRolesByUserId(Object id);
}
