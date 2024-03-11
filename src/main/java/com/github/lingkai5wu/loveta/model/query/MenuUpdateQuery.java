package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MenuUpdateQuery {
    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer pid;

    /**
     * 类型
     */
    private MenuTypeEnum type;

    /**
     * 标签
     */
    @Length(max = 20)
    private String label;

    /**
     * 路径
     */
    @Length(max = 255)
    private String path;
}
