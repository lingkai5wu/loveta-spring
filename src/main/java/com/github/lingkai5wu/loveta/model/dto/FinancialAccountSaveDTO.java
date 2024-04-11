package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增资金账户 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-12
 */
@Data
public class FinancialAccountSaveDTO {

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