package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Role;

import java.util.List;

/**
 * 角色 服务类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IRoleService extends IService<Role> {
    List<String> listRolesByUserId(Object id);
}
