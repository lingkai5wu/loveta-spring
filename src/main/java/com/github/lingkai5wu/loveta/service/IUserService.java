package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.User;
import com.github.lingkai5wu.loveta.model.vo.UserWithGroupsVO;

import java.util.List;

/**
 * 用户 服务类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
public interface IUserService extends IService<User> {

    User getUserByPhone(String phone);

    UserWithGroupsVO getUserWithGroupsVOById(long id);

    List<UserWithGroupsVO> listUserWithGroupsVOs();
}
