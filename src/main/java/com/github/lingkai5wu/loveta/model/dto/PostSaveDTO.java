package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 新增帖子 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Data
public class PostSaveDTO {

    /**
     * 板块ID
     */
    @NotNull
    private Integer forumId;

    /**
     * 标题
     */
    @NotBlank
    private String title;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 内容
     */
    @NotBlank
    private String content;

    /**
     * 附件
     */
    private List<String> attachment;
}
