package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.UserMapper;
import com.github.lingkai5wu.loveta.model.dto.BatchUpdateManyToManyDTO;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserByPhone(String phone) {
        return lambdaQuery()
                .eq(User::getPhone, phone)
                .one();
    }

    @Override
    public boolean existsById(int id) {
        return lambdaQuery()
                .eq(User::getId, id)
                .exists();
    }

    @Override
    public void batchUpdateUserRole(int userId, BatchUpdateManyToManyDTO<Integer> dto) {
        if (dto.getTargetIdsToInsert() != null && !dto.getTargetIdsToInsert().isEmpty()) {
            baseMapper.batchInsertUserRoles(userId, dto.getTargetIdsToInsert());
        }
        if (dto.getTargetIdsToDelete() != null && !dto.getTargetIdsToDelete().isEmpty()) {
            baseMapper.batchDeleteUserRoles(userId, dto.getTargetIdsToDelete());
        }
    }
}
