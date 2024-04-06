package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增板块 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-05
 */
@Data
public class ForumSaveDTO {

    /**
     * 标签
     */
    @NotBlank
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
}
