package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 板块 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-05
 */
@Data
public class ForumVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标签
     */
    @NotNull
    private String label;

    /**
     * 状态
     */
    @NotNull
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
    @NotNull
    private LocalDateTime createTime;
}
