package com.github.lingkai5wu.loveta.model.vo.exception;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class MethodArgumentNotValidExceptionVO {

    /**
     * 无效参数
     */
    @NotNull
    private Map<String, String> invalidArgument;
}

