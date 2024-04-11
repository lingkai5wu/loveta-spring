package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import com.github.lingkai5wu.loveta.enums.MaterialMovementTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 新增物资出入库记录 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-12
 */
@Data
public class MaterialMovementSaveDTO {

    /**
     * 库存ID
     */
    @NotNull
    private Integer stockId;

    /**
     * 变动类型
     */
    @NotNull
    private MaterialMovementTypeEnum movementType;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 操作用户ID
     */
    @NotNull
    private Integer operatorUserId;

    /**
     * 接收用户ID
     */
    private Integer referenceUserId;

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
     * 附件
     */
    private List<String> attachment;
}