package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.lingkai5wu.loveta.enums.UserSexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lingkai5wu
 * @since 2024-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("password")
    private String password;

    /**
     * 微信唯一标识
     */
    @JsonIgnore
    @TableField("wx_openid")
    private String wxOpenid;

    /**
     * 微信统一标识
     */
    @JsonIgnore
    @TableField("wx_unionid")
    private String wxUnionid;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 姓名
     */
    @TableField("realname")
    private String realname;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别
     */
    @TableField("sex")
    private UserSexEnum sex;


}
