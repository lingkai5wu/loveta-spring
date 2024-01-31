package com.github.lingkai5wu.loveta.model.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class ExceptionVO {
    /**
     * 异常类名
     */
    private String className;

    /**
     * 错误消息
     */
    private String message;
}
