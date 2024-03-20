package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleSaveDTO {

    /**
     * 角色编码
     */
    @NotBlank
    private String code;

    /**
     * 角色名
     */
    @NotBlank
    private String name;

    /**
     * 描述
     */
    private String description;
}
