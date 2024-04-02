package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改用户 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-13
 */
@Data
public class UserUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Long id;

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
