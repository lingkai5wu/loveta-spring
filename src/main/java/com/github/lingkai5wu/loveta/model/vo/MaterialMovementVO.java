package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.MaterialMovementTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资出入库记录 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class MaterialMovementVO {

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
     * 变动类型
     */
    @NotNull
    private MaterialMovementTypeEnum movementType;

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

    /**
     * 创建时间
     */
    @NotNull
    private LocalDateTime createTime;
}