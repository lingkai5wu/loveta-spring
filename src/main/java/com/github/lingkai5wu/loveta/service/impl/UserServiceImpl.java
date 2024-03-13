package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.UserMapper;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.vo.UserWithGroupsVO;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByPhone(String phone) {
        return lambdaQuery()
                .eq(User::getPhone, phone)
                .one();
    }

    @Override
    public UserWithGroupsVO getUserWithGroupsVOById(long id) {
        return userMapper.getUserWithGroupsVOById(id);
    }

    @Override
    public List<UserWithGroupsVO> listUserWithGroupsVOs() {
        return userMapper.listUserWithGroupsVOs();
    }
}
