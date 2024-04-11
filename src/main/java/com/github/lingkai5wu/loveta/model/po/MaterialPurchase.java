package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资采购记录
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
@Accessors(chain = true)
@TableName(value = "material_purchase", autoResultMap = true)
public class MaterialPurchase {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 出入库记录ID
     */
    private Integer movementId;

    /**
     * 收支明细ID
     */
    private Integer financialTransactionId;

    /**
     * 采购用户ID
     */
    private Integer procurementUserId;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 总价
     */
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> attachment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}