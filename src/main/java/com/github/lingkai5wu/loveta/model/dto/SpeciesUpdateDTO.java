package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改物种 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class SpeciesUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String description;
}
