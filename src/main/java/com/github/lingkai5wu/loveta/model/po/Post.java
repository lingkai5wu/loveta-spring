package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 帖子
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Data
@Accessors(chain = true)
@TableName(value = "post", autoResultMap = true)
public class Post {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 联系方式
     */
    private String contact;

    /**
     * 内容
     */
    private String content;

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
