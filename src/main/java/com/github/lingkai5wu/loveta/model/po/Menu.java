package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.*;
import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 菜单
 */
@Data
@Accessors(chain = true)
@TableName("menu")
public class Menu {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer pid;

    /**
     * 类型
     */
    private MenuTypeEnum type;

    /**
     * 标签
     */
    @TableField(condition = SqlCondition.LIKE)
    private String label;

    /**
     * 路径
     */
    @TableField(condition = SqlCondition.LIKE)
    private String path;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}