package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 帖子基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Data
public class PostBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 创建时间
     */
    @NotNull
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
