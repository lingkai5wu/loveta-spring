package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 物资库存 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialStockVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 物资ID
     */
    @NotNull
    private Integer materialId;

    /**
     * 仓库ID
     */
    @NotNull
    private Integer warehouseId;

    /**
     * 当前数量
     */
    @NotNull
    private Integer quantity;

    /**
     * 最低预警
     */
    private Integer min;

    /**
     * 最高上限
     */
    private Integer max;
}