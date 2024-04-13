package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增物资库存 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-13
 */
@Data
public class MaterialStockUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 物资ID
     */
    private Integer materialId;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 最低预警
     */
    private Integer min;

    /**
     * 最高上限
     */
    private Integer max;
}