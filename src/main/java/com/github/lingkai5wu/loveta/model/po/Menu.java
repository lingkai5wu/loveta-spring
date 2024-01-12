package com.github.lingkai5wu.loveta.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lingkai5wu.loveta.enums.MenuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lingkai5wu
 * @since 2024-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth_menu")
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    @TableField("pid")
    private Integer pid;

    /**
     * 菜单类型
     */
    @TableField("type")
    private MenuTypeEnum type;

    /**
     * 菜单名
     */
    @TableField("label")
    private String label;

    /**
     * 跳转目标
     */
    @TableField("target")
    private String target;


}
