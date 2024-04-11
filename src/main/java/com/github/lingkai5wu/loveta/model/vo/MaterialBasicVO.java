package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 物资基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Data
public class MaterialBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 分类
     */
    @NotNull
    private String category;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 名称
     */
    @NotNull
    private String name;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位
     */
    @NotNull
    private String unit;
}