package com.github.lingkai5wu.loveta.exception;

import cn.dev33.satoken.exception.*;
import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.exception.DisableServiceExceptionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SaTokenExceptionHandler {
    // 拦截: 未登录异常 + Http Basic 校验失败异常
    @ExceptionHandler({NotLoginException.class, NotBasicAuthException.class})
    public Result<Null> handlerException(NotLoginException e) {
        log.warn(e.getMessage());
        return Result.status(ResultStatusEnum.Unauthorized);
    }

    // 拦截: 缺少权限异常 + 缺少角色异常
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public Result<Null> handlerException(Exception e) {
        log.warn(e.getMessage());
        return Result.status(ResultStatusEnum.Forbidden);
    }

    // 拦截: 二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public Result<Null> handlerException(NotSafeException e) {
        log.warn(e.getMessage());
        return Result.error("二级认证校验失败");
    }

    // 拦截: 服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public Result<DisableServiceExceptionVO> handlerException(DisableServiceException e) {
        log.warn(e.getMessage());
        DisableServiceExceptionVO exceptionVO = new DisableServiceExceptionVO()
                .setService((String) e.getService())
                .setLevel(e.getLevel())
                .setDisableTime(e.getDisableTime());
        return Result.status(ResultStatusEnum.Forbidden, exceptionVO);
    }
}
