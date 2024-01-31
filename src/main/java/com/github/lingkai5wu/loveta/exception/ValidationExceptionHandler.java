package com.github.lingkai5wu.loveta.exception;

import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;
import com.github.lingkai5wu.loveta.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        Map<String, String> data = new LinkedHashMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            data.put(field, message);
        });
        return Result.status(ResultStatusEnum.BadRequest).setData(data);
    }
}