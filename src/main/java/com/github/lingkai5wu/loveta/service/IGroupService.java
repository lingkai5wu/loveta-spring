package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Group;

import java.util.List;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IGroupService extends IService<Group> {

    List<String> listRoleById(Object id);
}
