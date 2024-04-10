package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 物资仓库
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
@Accessors(chain = true)
@TableName("material_warehouse")
public class MaterialWarehouse {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 位置ID
     */
    private Integer locationId;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String description;

    /**
     * 保管用户ID
     */
    private Integer keeperUserId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}