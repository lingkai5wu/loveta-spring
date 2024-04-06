package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 动物 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
public class AnimalVO {

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
    @NotNull
    private AnimalStatusEnum status;

    /**
     * 物种ID
     */
    @NotNull
    private Integer speciesId;

    /**
     * 品种
     */
    private String breed;

    /**
     * 区域ID
     */
    private Integer areaId;

    /**
     * 性别
     */
    @NotNull
    private AnimalSexEnum sex;

    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;

    /**
     * 花色
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
     * 附件
     */
    private List<String> attachment;

    /**
     * 创建时间
     */
    @NotNull
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
