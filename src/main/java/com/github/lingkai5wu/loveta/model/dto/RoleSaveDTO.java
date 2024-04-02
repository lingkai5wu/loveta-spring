package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 新增角色 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-20
 */
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
