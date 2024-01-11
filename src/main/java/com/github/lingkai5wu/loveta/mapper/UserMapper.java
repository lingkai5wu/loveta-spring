package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface UserMapper extends BaseMapper<User> {

    UserVO getUserVOById(long id);

    List<UserVO> listUserVOs();
}
