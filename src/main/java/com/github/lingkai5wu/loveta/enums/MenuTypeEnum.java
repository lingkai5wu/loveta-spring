package com.github.lingkai5wu.loveta.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum MenuTypeEnum {
    PARENT(0),
    ROUTE(1),
    LINK(2);

    @EnumValue
    private final int value;
}
