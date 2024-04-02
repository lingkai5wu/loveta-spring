package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型 枚举类
 *
 * @author lingkai5wu
 * @since 2024-01-06
 */
@AllArgsConstructor
@Getter
public enum MenuTypeEnum {

    /**
     * 父菜单
     */
    PARENT(0),
    /**
     * 路由
     */
    ROUTE(1),
    /**
     * 链接
     */
    LINK(2);

    @EnumValue
    private final int value;
}
