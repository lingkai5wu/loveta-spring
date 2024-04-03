package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 位置 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Data
public class LocationVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 经度
     */
    @NotNull
    private BigDecimal lng;

    /**
     * 纬度
     */
    @NotNull
    private BigDecimal lat;
}
