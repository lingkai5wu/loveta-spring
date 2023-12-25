package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.GroupMapper;
import com.github.lingkai5wu.loveta.model.po.Group;
import com.github.lingkai5wu.loveta.service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户组 服务实现类
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Override
    public List<String> listRoleById(Object id) {
        return baseMapper.listRoleById(id);
    }
}
