package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.lingkai5wu.loveta.enums.MaterialMovementTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资出入库记录
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
@Accessors(chain = true)
@TableName(value = "material_movement", autoResultMap = true)
public class MaterialMovement {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 库存ID
     */
    private Integer stockId;

    /**
     * 变动类型
     */
    private MaterialMovementTypeEnum movementType;

    /**
     * 操作用户ID
     */
    private Integer operatorUserId;

    /**
     * 接收用户ID
     */
    private Integer referenceUserId;

    /**
     * 变动数量
     */
    private Integer quantity;

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