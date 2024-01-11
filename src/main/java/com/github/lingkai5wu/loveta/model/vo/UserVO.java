package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     */
    private Integer status;

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
    private LocalDateTime createTime;

    /**
     * 用户组
     */
    private List<String> groupList;
}
