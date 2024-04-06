package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 实体状态 枚举类
 *
 * @author lingkai5wu
 * @since 2024-04-06
 */
@AllArgsConstructor
@Getter
public enum EntityStatusEnum {

    /**
     * 草稿
     */
    DRAFT(0),

    /**
     * 等待
     */
    PENDING(1),

    /**
     * 锁定
     */
    LOCKED(2),

    /**
     * 通过
     */
    APPROVED(3),

    /**
     * 拒绝
     */
    REJECTED(4),

    /**
     * 隐藏
     */
    HIDDEN(5),

    /**
     * 归档
     */
    ARCHIVED(6),

    /**
     * 删除
     */
    DELETED(7);

    @EnumValue
    private final int value;
}
