package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 帖子 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Data
public class PostVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 板块ID
     */
    @NotNull
    private Integer forumId;

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
     * 用户ID
     */
    @NotNull
    private Integer userId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 内容
     */
    @NotNull
    private String content;

    /**
     * 附件
     */
    private List<String> attachment;

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
