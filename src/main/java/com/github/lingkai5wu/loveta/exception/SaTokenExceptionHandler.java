package com.github.lingkai5wu.loveta.exception;

import cn.dev33.satoken.exception.*;
import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;
import com.github.lingkai5wu.loveta.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SaTokenExceptionHandler {
    // 拦截: 未登录异常
    @ExceptionHandler(NotLoginException.class)
    public Result handlerException(NotLoginException e) {
        log.warn(e.getMessage());
        return Result.status(ResultStatusEnum.Unauthorized);
    }

    // 拦截: 缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public Result handlerException(NotPermissionException e) {
        log.warn(e.getMessage());
        Map<String, String> data = new LinkedHashMap<>();
        data.put("currentGroup", e.getLoginType());
        data.put("needPermission", e.getPermission());
        return Result.status(ResultStatusEnum.Forbidden).setData(data);
    }

    // 拦截: 缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public Result handlerException(NotRoleException e) {
        log.warn(e.getMessage());
        Map<String, String> data = new LinkedHashMap<>();
        data.put("currentGroup", e.getLoginType());
        data.put("needGroup", e.getRole());
        return Result.status(ResultStatusEnum.Forbidden).setData(data);
    }

    // 拦截: 二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public Result handlerException(NotSafeException e) {
        log.warn(e.getMessage());
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("service", e.getService());
        return Result.error("二级认证校验失败").setData(data);
    }

    // 拦截: 服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public Result handlerException(DisableServiceException e) {
        log.warn(e.getMessage());
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("service", e.getService());
        data.put("level", e.getLevel());
        data.put("expire", e.getLevel());
        return Result.status(ResultStatusEnum.Forbidden).setData(data);
    }

    // 拦截: Http Basic 校验失败异常
    @ExceptionHandler(NotBasicAuthException.class)
    public Result handlerException(NotBasicAuthException e) {
        log.warn(e.getMessage());
        return Result.status(ResultStatusEnum.Unauthorized);
    }
}
