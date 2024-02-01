package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 菜单
 */
@Data
@Accessors(chain = true)
@TableName("auth_menu")
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
    private String label;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}