package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.MaterialMovementTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 物资出入库记录基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialMovementBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 库存ID
     */
    @NotNull
    private Integer stockId;

    /**
     * 物资ID
     */
    @NotNull
    private Integer materialId;

    /**
     * 物资名称
     */
    @NotNull
    private String materialName;

    /**
     * 物资单位
     */
    @NotNull
    private String materialUnit;

    /**
     * 仓库ID
     */
    @NotNull
    private Integer warehouseId;

    /**
     * 仓库标签
     */
    @NotNull
    private String warehouseLabel;

    /**
     * 变动类型
     */
    @NotNull
    private MaterialMovementTypeEnum movementType;

    /**
     * 变动数量
     */
    @NotNull
    private Integer quantity;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @NotNull
    private LocalDateTime createTime;
}