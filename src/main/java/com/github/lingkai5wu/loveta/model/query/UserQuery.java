package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import lombok.Data;

/**
 * 用户
 */
@Data
public class UserQuery {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     */
    private UserStatusEnum status;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 性别
     */
    private UserSexEnum sex;
}