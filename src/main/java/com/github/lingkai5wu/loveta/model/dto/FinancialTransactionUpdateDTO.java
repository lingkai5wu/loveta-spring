package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.EntityStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 修改收支明细 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class FinancialTransactionUpdateDTO {

    /**
     * ID
     */
    @NotNull
    private Integer id;

    /**
     * 交易日期
     */
    private LocalDateTime date;

    /**
     * 操作用户ID
     */
    private Integer operatorUserId;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 费用类别ID
     */
    private Integer categoryId;

    /**
     * 交易金额
     */
    private Integer amount;

    /**
     * 状态
     */
    private EntityStatusEnum status;

    /**
     * 描述
     */
    private String description;

    /**
     * 附件
     */
    private List<String> attachment;
}