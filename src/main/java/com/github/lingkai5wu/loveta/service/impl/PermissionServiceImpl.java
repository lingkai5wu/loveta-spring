package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.PermissionMapper;
import com.github.lingkai5wu.loveta.model.dto.BatchUpdateManyToManyDTO;
import com.github.lingkai5wu.loveta.model.po.Permission;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.MethodsAnnotated;

/**
 * 权限 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<String> listPermissionCodesByUserId(Object id) {
        return baseMapper.listPermissionCodesByUserId(id);
    }

    @Override
    public List<Permission> listPermissionsByUserId(long id) {
        return baseMapper.listPermissionsByUserId(id);
    }

    @Override
    public List<Permission> listPermissionsByRoleId(int id) {
        return baseMapper.listPermissionsByRoleId(id);
    }

    @Override
    public void batchUpdateRolePermission(int roleId, BatchUpdateManyToManyDTO<Integer> dto) {
        if (dto.getTargetIdsToInsert() != null && !dto.getTargetIdsToInsert().isEmpty()) {
            baseMapper.batchInsertRolePermissions(roleId, dto.getTargetIdsToInsert());
        }
        if (dto.getTargetIdsToDelete() != null && !dto.getTargetIdsToDelete().isEmpty()) {
            baseMapper.batchDeleteRolePermissions(roleId, dto.getTargetIdsToDelete());
        }
    }

    @Override
    public Set<String> getPermissionCodeSetFromReflection() {
        Set<String> permissionCodeSet = new HashSet<>();
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("com.github.lingkai5wu.loveta.controller")
                        .setScanners(MethodsAnnotated));
        Set<Method> methodSet =
                reflections.get(MethodsAnnotated.with(SaCheckPermission.class).as(Method.class));
        methodSet.forEach(method -> {
            SaCheckPermission saCheckPermission = method.getAnnotation(SaCheckPermission.class);
            List<String> values = List.of(saCheckPermission.value());
            permissionCodeSet.addAll(values);
        });
        return permissionCodeSet;
    }

    @Override
    public void batchDeletePermissionsByCode(List<String> permissionCodeList) {
        if (permissionCodeList != null && !permissionCodeList.isEmpty()) {
            baseMapper.batchDeletePermissionsByCode(permissionCodeList);
        }
    }
}
