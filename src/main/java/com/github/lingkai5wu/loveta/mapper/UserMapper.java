package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.User;

import java.util.List;

/**
 * 用户 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface UserMapper extends BaseMapper<User> {

    boolean batchInsertUserRoles(int userId, List<Integer> roleIdsToInsert);

    boolean batchDeleteUserRoles(int userId, List<Integer> roleIdsToDelete);
}
