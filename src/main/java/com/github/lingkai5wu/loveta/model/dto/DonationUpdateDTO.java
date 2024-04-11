package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 修改捐赠 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-12
 */
@Data
public class DonationUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 捐赠方名称
     */
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