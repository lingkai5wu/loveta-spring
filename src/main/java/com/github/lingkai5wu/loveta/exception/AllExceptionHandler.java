package com.github.lingkai5wu.loveta.exception;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    public SaResult handlerException(Exception e) {
        log.warn("user: {}, {}\n{}\n{}", StpUtil.getLoginIdAsLong(), e.getClass().getName(), e.getMessage(), e.getStackTrace());
        return SaResult.error(e.getMessage());
    }
}
