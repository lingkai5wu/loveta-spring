package com.github.lingkai5wu.loveta.model;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 统一返回封装类
 *
 * @author lingkai5wu
 * @since 2024-01-31
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    /**
     * 状态码
     */
    @NotNull
    private Integer code;

    /**
     * 消息
     */
    @NotNull
    private String msg;

    /**
     * 数据
     */
    @NotNull
    private T data;

    private Result(HttpStatus status, T data) {
        this(status.value(), status.getReasonPhrase(), data);
    }

    public static <T> Result<T> ok() {
        return new Result<>(HttpStatus.OK, null);
    }

    public static <T> Result<T> data(T data) {
        return new Result<>(HttpStatus.OK, data);
    }

    public static <T> Result<PageVO<T>> page(List<T> records, Page<?> page) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtil.copyProperties(page, pageVO);
        pageVO.setRecords(records);
        return new Result<>(HttpStatus.OK, pageVO);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    public static <T> Result<T> status(HttpStatus status) {
        return new Result<>(status, null);
    }

    public static <T> Result<T> status(HttpStatus status, String msg) {
        return new Result<>(status.value(), msg, null);
    }

    public static <T> Result<T> status(HttpStatus status, String msg, T data) {
        return new Result<>(status.value(), msg, data);
    }
}