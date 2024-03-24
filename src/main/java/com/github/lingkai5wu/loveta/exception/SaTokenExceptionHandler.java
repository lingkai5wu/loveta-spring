package com.github.lingkai5wu.loveta.exception;

import cn.dev33.satoken.exception.*;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.exception.DisableServiceExceptionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SaTokenExceptionHandler {

    /**
     * 未登录
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<String> handlerException(NotLoginException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * Http Basic 校验失败
     */
    @ExceptionHandler(NotBasicAuthException.class)
    public Result<String> handlerException(NotBasicAuthException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 缺少权限
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result<String> handlerException(NotPermissionException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * 缺少角色
     */
    @ExceptionHandler(NotRoleException.class)
    public Result<String> handlerException(NotRoleException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * 二级认证校验失败
     */
    @ExceptionHandler(NotSafeException.class)
    public Result<String> handlerException(NotSafeException e) {
        log.warn(e.getMessage());
        return Result.status(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * 服务封禁
     */
    @ExceptionHandler(DisableServiceException.class)
    public Result<DisableServiceExceptionVO> handlerException(DisableServiceException e) {
        log.warn(e.getMessage());
        DisableServiceExceptionVO exceptionVO = new DisableServiceExceptionVO()
                .setService((String) e.getService())
                .setLevel(e.getLevel())
                .setDisableTime(e.getDisableTime());
        return Result.status(HttpStatus.FORBIDDEN, e.getMessage(), exceptionVO);
    }
}
