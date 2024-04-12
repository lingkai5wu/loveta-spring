package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 物资库存
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
@Accessors(chain = true)
@TableName("material_stock")
public class MaterialStock {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物资ID
     */
    private Integer materialId;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 当前数量
     */
    private Integer quantity;

    /**
     * 最低预警
     */
    private Integer min;

    /**
     * 最高上限
     */
    private Integer max;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}