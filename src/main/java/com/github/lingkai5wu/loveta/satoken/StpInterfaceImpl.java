package com.github.lingkai5wu.loveta.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.github.lingkai5wu.loveta.service.IRoleService;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    private final IRoleService roleService;
    private final IPermissionService permissionService;

    public StpInterfaceImpl(IRoleService roleService, IPermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return permissionService.listPermissionsByUserId(loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return roleService.listRolesByUserId(loginId);
    }

}
