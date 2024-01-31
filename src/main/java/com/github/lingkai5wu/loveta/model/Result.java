package com.github.lingkai5wu.loveta.model;

import com.github.lingkai5wu.loveta.enums.ResultStatusEnum;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 结果
 */
public class Result extends LinkedHashMap<String, Object> implements Serializable {
    public Result(int code, String msg, Object data) {
        this.put("code", code);
        this.setMsg(msg);
        this.setData(data);
    }

    public Result setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    public Result setData(Object data) {
        this.put("data", data);
        return this;
    }

    public static Result ok() {
        return status(ResultStatusEnum.OK);
    }

    public static Result data(Object data) {
        return status(ResultStatusEnum.OK).setData(data);
    }

    public static Result error() {
        return status(ResultStatusEnum.InternalServerError);
    }

    public static Result error(String msg) {
        return status(ResultStatusEnum.InternalServerError).setMsg(msg);
    }

    public static Result status(ResultStatusEnum statusEnum) {
        return new Result(statusEnum.getCode(), statusEnum.getMsg(), null);
    }
}