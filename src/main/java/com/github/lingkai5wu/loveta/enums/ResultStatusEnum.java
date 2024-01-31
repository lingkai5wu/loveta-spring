package com.github.lingkai5wu.loveta.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果状态枚举
 * 如需更新，参考 <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status">这里</a>
 */
@AllArgsConstructor
@Getter
public enum ResultStatusEnum {
    OK(200, "成功"),
    BadRequest(400, "请求错误"),
    Unauthorized(401, "未登录"),
    Forbidden(403, "无权限"),
    InternalServerError(500, "服务器错误");

    private final int code;
    private final String msg;
}
