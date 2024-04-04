package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增物种 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class SpeciesSaveDTO {

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
