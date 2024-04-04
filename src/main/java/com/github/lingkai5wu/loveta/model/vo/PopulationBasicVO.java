package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 种群 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class PopulationBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 物种
     */
    @NotNull
    private String species;

    /**
     * 区域
     */
    @NotNull
    private String area;

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
