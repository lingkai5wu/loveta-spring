package com.github.lingkai5wu.loveta.model;

import lombok.Data;

/**
 * 分页 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-26
 */
@Data
public class PageDTO {

    /**
     * 当前页数
     */
    private Long current;

    /**
     * 分页大小
     */
    private Long size;
}
