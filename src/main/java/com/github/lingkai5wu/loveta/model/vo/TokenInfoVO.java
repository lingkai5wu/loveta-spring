package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 令牌信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-02-01
 */
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
