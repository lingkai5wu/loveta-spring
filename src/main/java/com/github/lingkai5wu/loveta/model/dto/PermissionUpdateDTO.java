package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 权限
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