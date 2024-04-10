package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.AnimalSexEnum;
import com.github.lingkai5wu.loveta.enums.AnimalStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 动物 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Data
public class AnimalBasicVO {

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
     * 分类ID
     */
    @NotNull
    private String categoryId;

    /**
     * 品种
     */
    private String breed;

    /**
     * 区域
     */
    private String areaId;

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
     * 头像
     */
    private String avatar;

    /**
     * 横幅
     */
    private String banner;
}
