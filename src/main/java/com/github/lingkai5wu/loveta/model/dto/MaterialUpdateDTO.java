package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 修改物资 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
public class MaterialUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 名称
     */
    private String name;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位
     */
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