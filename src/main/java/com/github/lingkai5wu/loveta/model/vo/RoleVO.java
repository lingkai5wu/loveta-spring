package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 角色 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-03-18
 */
@Data
public class RoleVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 角色编码
     */
    @NotNull
    private String code;

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