package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态 枚举类
 *
 * @author lingkai5wu
 * @since 2024-03-13
 */
@AllArgsConstructor
@Getter
public enum UserStatusEnum {

    /**
     * 未验证
     */
    UNCONFIRMED(0),
    /**
     * 正常
     */
    CONFIRMED(1),
    /**
     * 封禁
     */
    BANNED(2),
    /**
     * 删除
     */
    DELETED(3);

    @EnumValue
    private final int value;
}
