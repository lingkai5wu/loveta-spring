package com.github.lingkai5wu.loveta.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 分页 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-03-26
 */
@Data
public class PageVO<T> {

    /**
     * 当前页数
     */
    @NotNull
    private Long current;

    /**
     * 分页大小
     */
    @NotNull
    private Long size;

    /**
     * 记录条数
     */
    @NotNull
    private Long total;

    /**
     * 记录列表
     */
    @NotNull
    private List<T> records;
}
