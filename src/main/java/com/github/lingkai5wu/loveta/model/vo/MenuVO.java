package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuVO {
    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 父菜单ID
     */
    @NotNull
    private Integer pid;

    /**
     * 类型
     */
    @NotNull
    private MenuTypeEnum type;

    /**
     * 标签
     */
    @NotNull
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
