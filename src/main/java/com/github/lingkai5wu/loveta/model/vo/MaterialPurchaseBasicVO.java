package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 物资采购记录基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialPurchaseBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 出入库记录ID
     */
    @NotNull
    private Integer movementId;

    /**
     * 收支明细ID
     */
    private Integer financialTransactionId;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 总价
     */
    @NotNull
    private Integer totalAmount;

    /**
     * 描述
     */
    private String description;
}