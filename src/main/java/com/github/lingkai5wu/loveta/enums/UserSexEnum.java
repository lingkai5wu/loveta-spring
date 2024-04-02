package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户性别 枚举类
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
@AllArgsConstructor
@Getter
public enum UserSexEnum {

    /**
     * 男
     */
    MALE(0),
    /**
     * 女
     */
    FEMALE(1),
    /**
     * 其他性别
     */
    OTHER(2),
    /**
     * 不愿说明
     */
    PREFER_NOT_TO_SAY(3),
    /**
     * 非二元性别
     */
    NEUTRAL(4);

    @EnumValue
    private final int value;
}
