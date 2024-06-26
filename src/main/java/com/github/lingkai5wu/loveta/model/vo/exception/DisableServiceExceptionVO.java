package com.github.lingkai5wu.loveta.model.vo.exception;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 服务封禁 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-02-01
 */
@Data
@Accessors(chain = true)
public class DisableServiceExceptionVO {

    /**
     * 服务
     */
    @NotNull
    private String service;

    /**
     * 等级
     */
    @NotNull
    private int level;

    /**
     * 封禁时长
     */
    @NotNull
    private long disableTime;
}
