package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改实体排序 数据传输对象
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Data
public class EntityOrderUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 父实体ID
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer sortOrder;
}
