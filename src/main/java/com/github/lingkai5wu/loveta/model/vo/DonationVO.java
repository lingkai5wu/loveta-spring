package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 捐赠 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class DonationVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 捐赠方名称
     */
    @NotNull
    private String donorName;

    /**
     * 捐赠用户ID
     */
    private Integer donorUserId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 捐赠时间
     */
    @NotNull
    private LocalDateTime date;

    /**
     * 描述
     */
    private String description;

    /**
     * 收支明细ID
     */
    private Integer financialTransactionId;

    /**
     * 物资出入库记录ID
     */
    private Integer materialMovementId;
}