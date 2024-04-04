package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增种群 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class PopulationSaveDTO {

    /**
     * 物种ID
     */
    @NotNull
    private Integer speciesId;

    /**
     * 区域ID
     */
    @NotNull
    private Integer areaId;

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
