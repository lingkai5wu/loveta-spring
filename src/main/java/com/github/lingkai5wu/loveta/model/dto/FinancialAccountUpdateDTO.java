package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改资金账户 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-12
 */
@Data
public class FinancialAccountUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 账户名
     */
    private String name;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 当前余额
     */
    private Integer balance;

    /**
     * 描述
     */
    private String description;
}