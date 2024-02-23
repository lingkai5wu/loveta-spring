package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TokenInfoVO {
    /**
     * Token 名
     */
    @NotNull
    private String tokenName;

    /**
     * Token 值
     */
    @NotNull
    private String tokenValue;
}
