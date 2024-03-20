package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import lombok.Data;

@Data
public class MenuQuery {

    /**
     * 类型
     */
    private MenuTypeEnum type;

    /**
     * 标签
     */
    private String label;

    /**
     * 路径
     */
    private String path;
}
