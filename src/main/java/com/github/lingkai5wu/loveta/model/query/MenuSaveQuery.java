package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuSaveQuery {
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
    @NotBlank
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
