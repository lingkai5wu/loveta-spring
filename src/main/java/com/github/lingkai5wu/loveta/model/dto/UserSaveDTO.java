package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增用户 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-13
 */
@Data
public class UserSaveDTO {

    /**
     * 手机号
     */
    @NotBlank
    private String phone;

    /**
     * 状态
     */
    @NotNull
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
