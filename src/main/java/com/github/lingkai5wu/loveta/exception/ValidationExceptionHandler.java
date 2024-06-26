package com.github.lingkai5wu.loveta.exception;

import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.exception.MethodArgumentNotValidExceptionVO;
import com.github.lingkai5wu.loveta.service.IExceptionService;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class ValidationExceptionHandler {

    private final IExceptionService exceptionService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<MethodArgumentNotValidExceptionVO> handlerException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.BAD_REQUEST, "参数无效",
                exceptionService.getMethodArgumentNotValidExceptionVO(e));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handlerException(ConstraintViolationException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
