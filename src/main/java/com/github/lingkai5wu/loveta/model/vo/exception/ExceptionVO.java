package com.github.lingkai5wu.loveta.model.vo.exception;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExceptionVO {

    /**
     * 异常类名
     */
    @NotNull
    private String className;

    /**
     * 错误消息
     */
    @NotNull
    private String message;
}
