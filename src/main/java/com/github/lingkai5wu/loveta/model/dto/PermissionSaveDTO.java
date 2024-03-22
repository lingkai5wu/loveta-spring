package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 权限
 */
@Data
public class PermissionSaveDTO {
    /**
     * 权限编码
     */
    @NotBlank
    private String code;

    /**
     * 描述
     */
    private String description;
}