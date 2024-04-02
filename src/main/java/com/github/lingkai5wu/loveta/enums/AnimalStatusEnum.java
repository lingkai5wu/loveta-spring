package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动物状态 枚举类
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@AllArgsConstructor
@Getter
public enum AnimalStatusEnum {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 失踪
     */
    MISSING(1),
    /**
     * 领养
     */
    ADOPTED(2),
    /**
     * 死亡
     */
    DECEASED(3);

    @EnumValue
    private final int value;
}
