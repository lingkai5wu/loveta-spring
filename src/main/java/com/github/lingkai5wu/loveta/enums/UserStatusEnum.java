package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
