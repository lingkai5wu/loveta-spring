package com.github.lingkai5wu.loveta.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.github.lingkai5wu.loveta.service.IPermissionService;
import com.github.lingkai5wu.loveta.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限加载 接口实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Component
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final IRoleService roleService;
    private final IPermissionService permissionService;

    /**
     * 返回一个账号所拥有的权限编码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return permissionService.listPermissionCodesByUserId(loginId);
    }

    /**
     * 返回一个账号所拥有的角色编码集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return roleService.listRoleCodesByUserId(loginId);
    }
}