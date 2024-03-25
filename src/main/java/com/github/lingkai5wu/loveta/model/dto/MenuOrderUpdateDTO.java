package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuOrderUpdateDTO {

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
     * 排序
     */
    private Integer sortOrder;
}
