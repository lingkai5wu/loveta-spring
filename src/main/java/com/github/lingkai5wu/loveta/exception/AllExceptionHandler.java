package com.github.lingkai5wu.loveta.exception;

import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.ExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {
    private final IExceptionService exceptionService;

    public AllExceptionHandler(IExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.warn(e.getMessage());
        ExceptionVO exceptionVO = exceptionService.getExceptionVO(e);
        return Result.error().setData(exceptionVO);
    }
}
