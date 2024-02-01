package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuUpdateQuery {
    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer pid;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 标签
     */
    private String label;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;
}
