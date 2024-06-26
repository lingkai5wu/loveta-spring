package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Permission;

import java.util.List;

/**
 * 权限 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> listPermissionCodesByUserId(Object id);

    List<Permission> listPermissionsByUserId(int id);

    List<Permission> listPermissionsByRoleId(int id);

    boolean batchInsertRolePermissions(int roleId, List<Integer> permissionIdsToInsert);

    boolean batchDeleteRolePermissions(int roleId, List<Integer> permissionIdsToDelete);

    boolean batchDeletePermissionsByCode(List<String> permissionCodeList);
}
