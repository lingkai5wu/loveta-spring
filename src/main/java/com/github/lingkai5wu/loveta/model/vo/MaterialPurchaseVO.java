package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 物资采购记录 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialPurchaseVO {

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
     * 采购用户ID
     */
    @NotNull
    private Integer procurementUserId;

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
     * 供应商
     */
    private String supplier;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 描述
     */
    private String description;

    /**
     * 附件
     */
    private List<String> attachment;
}