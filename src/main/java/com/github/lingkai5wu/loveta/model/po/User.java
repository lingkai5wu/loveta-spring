package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.*;
import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import com.github.lingkai5wu.loveta.enums.UserStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户 数据源对象
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class User {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    @TableField(condition = SqlCondition.LIKE)
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
    @TableField(condition = SqlCondition.LIKE)
    private String nickname;

    /**
     * 姓名
     */
    @TableField(condition = SqlCondition.LIKE)
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