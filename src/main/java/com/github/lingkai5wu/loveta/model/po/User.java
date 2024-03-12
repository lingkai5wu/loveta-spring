package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户
 */
@Data
@Accessors(chain = true)
@TableName("auth_user")
public class User {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信唯一标识
     */
    private String wxOpenid;

    /**
     * 微信统一标识
     */
    private String wxUnionid;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}