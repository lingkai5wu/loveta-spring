package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Permission;

import java.util.List;

/**
 * 权限 服务类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IPermissionService extends IService<Permission> {

    List<String> listPermissionsByUserId(Object id);
}
