package com.github.lingkai5wu.loveta.model;

import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 结果
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    private Result(ResultStatusEnum statusEnum, T data) {
        this(statusEnum.getCode(), statusEnum.getMsg(), data);
    }

    public static <T> Result<T> ok() {
        return new Result<>(ResultStatusEnum.OK, null);
    }

    public static <T> Result<T> data(T data) {
        return new Result<>(ResultStatusEnum.OK, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultStatusEnum.InternalServerError.getCode(), msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultStatusEnum.InternalServerError.getCode(), msg, data);
    }

    public static <T> Result<T> status(ResultStatusEnum statusEnum) {
        return new Result<>(statusEnum, null);
    }

    public static <T> Result<T> status(ResultStatusEnum statusEnum, T data) {
        return new Result<>(statusEnum, data);
    }

    public static <T> Result<T> status(ResultStatusEnum statusEnum, String msg, T data) {
        return new Result<>(statusEnum.getCode(), msg, data);
    }
}