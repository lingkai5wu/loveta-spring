package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 种群统计信息
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Data
@Accessors(chain = true)
@TableName("population_stat")
public class PopulationStat {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 种群ID
     */
    private Integer populationId;

    /**
     * 区域ID
     */
    private Integer areaId;

    /**
     * 开始时间
     */
    private LocalDate startDate;

    /**
     * 结束时间
     */
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
