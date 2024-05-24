package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 动物
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
@Accessors(chain = true)
@TableName(value = "animal", autoResultMap = true)
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
     * 分类ID
     */
    private Integer categoryId;

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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> attachment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
