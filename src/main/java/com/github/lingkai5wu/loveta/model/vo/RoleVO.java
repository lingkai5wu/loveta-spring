package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 角色
 */
@Data
public class RoleVO {
    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 角色名
     */
    @NotNull
    private String name;

    /**
     * 描述
     */
    private String description;
}