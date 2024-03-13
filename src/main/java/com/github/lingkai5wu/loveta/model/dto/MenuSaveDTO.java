package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MenuSaveDTO {
    /**
     * 父菜单ID
     */
    @NotNull
    private Integer pid;

    /**
     * 类型
     */
    @NotNull
    private MenuTypeEnum type;

    /**
     * 标签
     */
    @NotBlank
    @Length(max = 20)
    private String label;

    /**
     * 路径
     */
    @Length(max = 255)
    private String path;
}
