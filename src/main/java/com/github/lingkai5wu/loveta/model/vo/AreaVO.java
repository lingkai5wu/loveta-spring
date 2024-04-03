package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 区域 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Data
public class AreaVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标签
     */
    @NotNull
    private String label;

    /**
     * 描述
     */
    private String description;

    /**
     * 多边形顶点
     */
    private List<LocationVO> locations;
}
