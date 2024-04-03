package com.github.lingkai5wu.loveta.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增或更新位置 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Data
public class LocationSaveOrUpdateDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;
}
