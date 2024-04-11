package com.github.lingkai5wu.loveta.model.vo;

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
    private Integer id;

    /**
     * 物资
     */
    private MaterialBasicVO material;

    /**
     * 仓库
     */
    private String warehouse;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 当前数量
     */
    private Integer current;

    /**
     * 最低预警
     */
    private Integer min;

    /**
     * 最高上限
     */
    private Integer max;
}