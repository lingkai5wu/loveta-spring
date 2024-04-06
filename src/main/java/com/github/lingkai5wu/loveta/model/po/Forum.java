package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 板块
 *
 * @author lingkai5wu
 * @since 2024-04-05
 */
@Data
@Accessors(chain = true)
@TableName("forum")
public class Forum {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签
     */
    private String label;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 描述
     */
    private String description;

    /**
     * 公告
     */
    private String notice;

    /**
     * 版头
     */
    private String header;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
