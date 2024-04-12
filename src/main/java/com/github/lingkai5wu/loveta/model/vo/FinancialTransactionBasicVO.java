package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 收支明细基本信息 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class FinancialTransactionBasicVO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 交易日期
     */
    @NotNull
    private LocalDateTime date;

    /**
     * 操作用户
     */
    @NotNull
    private UserBasicVO operatorUser;

    /**
     * 费用类别
     */
    @NotNull
    private String category;

    /**
     * 交易金额
     */
    @NotNull
    private Integer amount;

    /**
     * 描述
     */
    private String description;

    /**
     * 附件
     */
    private List<String> attachment;
}