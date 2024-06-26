package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分类 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class CategoryVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标签
     */
    @NotNull
    private String label;

    /**
     * 描述
     */
    private String description;
}
