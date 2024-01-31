package com.github.lingkai5wu.loveta.model.vo.exception;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DisableServiceExceptionVO {
    /**
     * 服务
     */
    private String service;

    /**
     * 等级
     */
    private int level;

    /**
     * 封禁时长
     */
    private long disableTime;
}
