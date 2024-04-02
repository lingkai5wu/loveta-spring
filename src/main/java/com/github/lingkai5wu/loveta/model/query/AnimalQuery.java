package com.github.lingkai5wu.loveta.model.query;

import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * 动物 数据查询对象
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
public class AnimalQuery {

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
     * 电子标签
     */
    private String rfid;
}
