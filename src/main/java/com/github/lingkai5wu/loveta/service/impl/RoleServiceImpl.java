package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.RoleMapper;
import com.github.lingkai5wu.loveta.model.po.Role;
import com.github.lingkai5wu.loveta.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<String> listRolesByUserId(Object id) {
        return baseMapper.listRolesByUserId(id);
    }
}
