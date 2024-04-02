package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改权限 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-22
 */
@Data
public class PermissionUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 描述
     */
    private String description;
}