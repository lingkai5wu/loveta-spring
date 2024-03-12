package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {
    /**
     * ID
     */
    @NotNull
    private Long id;

    /**
     * 手机号
     */
    @NotNull
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

    /**
     * 创建时间
     */
    @NotNull
    private LocalDateTime createTime;

    /**
     * 用户组
     */
    private List<String> groupList;
}
