package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 物资出入库变动类型 枚举类
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@AllArgsConstructor
@Getter
public enum MaterialMovementTypeEnum {

    /**
     * 采购
     */
    PROCUREMENT(0),
    /**
     * 借入
     */
    BORROWING(1),
    /**
     * 捐赠
     */
    DONATION(2),
    /**
     * 借出
     */
    LENDING(3),
    /**
     * 使用
     */
    USAGE(4),
    /**
     * 丢失
     */
    LOST(5),
    /**
     * 调拨
     */
    TRANSFER(6),
    /**
     * 报废
     */
    SCRAP(7);

    @EnumValue
    private final int value;
}