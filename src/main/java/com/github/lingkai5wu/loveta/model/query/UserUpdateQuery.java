package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateQuery {
    /**
     * ID
     */
    @NotNull
    private Long id;

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
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private UserSexEnum sex;
}
