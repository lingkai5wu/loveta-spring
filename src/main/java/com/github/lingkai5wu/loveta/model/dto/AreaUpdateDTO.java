package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 修改区域 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Data
public class AreaUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String description;

    /**
     * 多边形顶点
     */
    private List<LocationSaveOrUpdateDTO> locations;
}
