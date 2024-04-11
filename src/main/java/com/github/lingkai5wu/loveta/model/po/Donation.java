package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 捐赠
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
@Accessors(chain = true)
@TableName("donation")
public class Donation {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}