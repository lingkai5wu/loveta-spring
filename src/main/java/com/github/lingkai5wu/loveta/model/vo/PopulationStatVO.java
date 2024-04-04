package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 种群统计信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
public class PopulationStatVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 种群ID
     */
    @NotNull
    private Integer populationId;

    /**
     * 区域ID
     */
    @NotNull
    private Integer areaId;

    /**
     * 开始时间
     */
    @NotNull
    private LocalDate startDate;

    /**
     * 结束时间
     */
    @NotNull
    private LocalDate endDate;

    /**
     * 个体数量
     */
    private Integer individualCount;

    /**
     * 出生率
     */
    private Integer birthRate;

    /**
     * 死亡率
     */
    private Integer deathRate;

    /**
     * 迁入率
     */
    private Integer immigrationRate;

    /**
     * 迁出率
     */
    private Integer emigrationRate;
}
