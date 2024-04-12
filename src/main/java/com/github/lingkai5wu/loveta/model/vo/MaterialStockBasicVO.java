package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 物资库存基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialStockBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 物资
     */
    @NotNull
    private MaterialBasicVO material;

    /**
     * 仓库
     */
    @NotNull
    private String warehouse;

    /**
     * 联系方式
     */
    private String contact;

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