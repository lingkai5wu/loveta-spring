package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.dto.BatchUpdateManyToManyDTO;
import com.github.lingkai5wu.loveta.model.po.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限 服务类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IPermissionService extends IService<Permission> {

    List<String> listPermissionCodesByUserId(Object id);

    List<Permission> listPermissionsByUserId(long id);

    List<Permission> listPermissionsByRoleId(int id);

    void batchUpdateRolePermission(int roleId, BatchUpdateManyToManyDTO<Integer> dto);

    Set<String> getPermissionCodeSetFromReflection();

    void batchDeletePermissionsByCode(List<String> inDbNotInReflection);
}
