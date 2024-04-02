package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 动物 数据源对象
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
@Accessors(chain = true)
@TableName("animal")
public class Animal {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
