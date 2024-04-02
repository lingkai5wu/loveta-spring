package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动物性别 枚举类
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@AllArgsConstructor
@Getter
public enum AnimalSexEnum {

    /**
     * 公
     */
    MALE(0),
    /**
     * 母
     */
    FEMALE(1),
    /**
     * 其他性别
     */
    OTHER(2),
    /**
     * 未知
     */
    UNKNOWN(3);

    @EnumValue
    private final int value;
}
