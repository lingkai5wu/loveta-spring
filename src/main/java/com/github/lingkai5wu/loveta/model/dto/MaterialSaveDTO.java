package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 新增物资 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
public class MaterialSaveDTO {

    /**
     * 分类ID
     */
    @NotNull
    private Integer categoryId;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 名称
     */
    @NotBlank
    private String name;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位
     */
    @NotBlank
    private String unit;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件
     */
    private List<String> attachment;
}