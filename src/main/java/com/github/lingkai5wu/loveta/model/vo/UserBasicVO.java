package com.github.lingkai5wu.loveta.model.vo;

import cn.hutool.core.util.DesensitizedUtil;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
@Data
public class UserBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 手机号
     */
    @NotNull
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    public String getPhone() {
        return DesensitizedUtil.mobilePhone(phone);
    }
}
