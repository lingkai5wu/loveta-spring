package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import lombok.Data;

/**
 * 帖子 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Data
public class PostQuery {

    /**
     * 板块ID
     */
    private Integer forumId;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;
}
