package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 菜单 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-02-01
 */
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
     * 路径
     */
    private String path;

    /**
     * 排序
     */
    private Integer sortOrder;
}
