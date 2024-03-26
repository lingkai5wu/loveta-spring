package com.github.lingkai5wu.loveta.model;

import lombok.Data;

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
