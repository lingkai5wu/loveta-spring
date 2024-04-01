package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
