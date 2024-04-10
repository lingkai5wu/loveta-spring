package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 对象存储图片处理样式 枚举类
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@AllArgsConstructor
@Getter
public enum OssImageProcessStyleEnum {

    /**
     * 原图
     */
    NONE(null),
    /**
     * 普通
     */
    NORMAL("normal"),
    /**
     * 头像
     */
    AVATAR("avatar");

    @EnumValue
    private final String name;
}
