package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserSexEnum {
    MALE(0),
    FEMALE(1),
    OTHER(2),
    PREFER_NOT_TO_SAY(3),
    NEUTRAL(4);

    @EnumValue
    private final int value;
}
