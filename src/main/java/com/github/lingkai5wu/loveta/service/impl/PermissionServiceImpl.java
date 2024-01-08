package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.PermissionMapper;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<String> listPermissionsById(Object id) {
        return baseMapper.listPermissionsById(id);
    }
}
