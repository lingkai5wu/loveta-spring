package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 权限
 */
@Data
public class PermissionVO {
    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 权限编码
     */
    @NotNull
    private String code;

    /**
     * 描述
     */
    private String description;
}