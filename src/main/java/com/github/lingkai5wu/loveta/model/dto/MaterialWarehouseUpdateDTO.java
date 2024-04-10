package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 更新物资仓库 数据传输对象对象
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
public class MaterialWarehouseUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 位置ID
     */
    private Integer locationId;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String description;

    /**
     * 保管用户ID
     */
    private Integer keeperUserId;

    /**
     * 联系方式
     */
    private String contact;
}