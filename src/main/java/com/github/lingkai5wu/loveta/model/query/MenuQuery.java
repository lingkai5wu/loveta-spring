package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import lombok.Data;

/**
 * 菜单 数据查询对象
 *
 * @author lingkai5wu
 * @since 2024-03-20
 */
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
