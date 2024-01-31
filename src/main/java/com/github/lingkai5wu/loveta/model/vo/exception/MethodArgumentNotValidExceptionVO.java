package com.github.lingkai5wu.loveta.model.vo.exception;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class MethodArgumentNotValidExceptionVO {
    private Map<String, String> invalidArgument;
}

