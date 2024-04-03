package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增位置 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Data
public class LocationSaveDTO {

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
