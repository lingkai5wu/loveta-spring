package com.github.lingkai5wu.loveta.exception;

import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.exception.ExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class AllExceptionHandler {

    private final IExceptionService exceptionService;

    @ExceptionHandler(Exception.class)
    public Result<ExceptionVO> handlerException(Exception e) {
        log.warn(e.getMessage());
        e.printStackTrace();
        ExceptionVO exceptionVO = exceptionService.getExceptionVO(e);
        return Result.status(HttpStatus.INTERNAL_SERVER_ERROR, "服务器错误", exceptionVO);
    }
}
