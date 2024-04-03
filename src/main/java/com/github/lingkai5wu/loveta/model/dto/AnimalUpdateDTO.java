package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 修改动物 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
public class AnimalUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态
     */
    private AnimalStatusEnum status;

    /**
     * 种群ID
     */
    private Integer populationId;

    /**
     * 品种
     */
    private String breed;

    /**
     * 性别
     */
    private AnimalSexEnum sex;

    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;

    /**
     * 颜色
     */
    private String color;

    /**
     * 特征
     */
    private String feature;

    /**
     * 健康状况
     */
    private String health;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 电子标签
     */
    private String rfid;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 横幅
     */
    private String banner;
}