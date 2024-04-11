package com.github.lingkai5wu.loveta.model.vo;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 资金账户 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class FinancialAccountVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 账户名
     */
    @NotNull
    private String name;

    /**
     * 状态
     */
    @NotNull
    private EntityStatusEnum status;

    /**
     * 用户ID
     */
    @NotNull
    private Integer userId;

    /**
     * 当前余额
     */
    @NotNull
    private Integer balance;

    /**
     * 描述
     */
    private String description;
}