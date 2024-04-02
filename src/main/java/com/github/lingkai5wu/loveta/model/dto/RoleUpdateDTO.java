package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改角色 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-20
 */
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
