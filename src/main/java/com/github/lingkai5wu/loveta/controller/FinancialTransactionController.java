package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.FinancialTransactionSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.FinancialTransactionUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.FinancialTransaction;
import com.github.lingkai5wu.loveta.model.vo.FinancialTransactionBasicVO;
import com.github.lingkai5wu.loveta.model.vo.FinancialTransactionVO;
import com.github.lingkai5wu.loveta.service.IFinancialAccountService;
import com.github.lingkai5wu.loveta.service.IFinancialTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收支明细
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/financial-transactions")
@AllArgsConstructor
public class FinancialTransactionController {

    private final IFinancialTransactionService financialTransactionService;
    private final IFinancialAccountService financialAccountService;

    /**
     * 获取收支明细
     */
    @GetMapping("/{id}")
    @SaCheckPermission("financial:transaction:get")
    public Result<FinancialTransactionVO> getFinancialTransactionVO(@PathVariable int id) {
        FinancialTransaction financialTransaction = financialTransactionService.getById(id);
        if (financialTransaction == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        FinancialTransactionVO financialTransactionVO = BeanUtil.copyProperties(financialTransaction, FinancialTransactionVO.class);
        return Result.data(financialTransactionVO);
    }

    /**
     * 列出全部收支明细基本信息
     */
    @GetMapping
    @SaCheckPermission("financial:transaction:list")
    public Result<List<FinancialTransactionBasicVO>> listFinancialTransactionBasicVOs() {
        List<FinancialTransactionBasicVO> financialTransactionBasicVOList = financialTransactionService.listFinancialTransactionBasicVOs();
        return Result.data(financialTransactionBasicVOList);
    }

    /**
     * 新增收支明细
     */
    @PostMapping
    @SaCheckPermission("financial:transaction:save")
    @Transactional
    public Result<Void> saveFinancialTransaction(@RequestBody @Validated FinancialTransactionSaveDTO dto) {
        FinancialTransaction financialTransaction = BeanUtil.copyProperties(dto, FinancialTransaction.class);
        financialAccountService.updateByTransaction(financialTransaction);
        financialTransactionService.save(financialTransaction);
        return Result.ok();
    }

    /**
     * 修改收支明细
     */
    @PutMapping
    @SaCheckPermission("financial:transaction:update")
    @Transactional
    public Result<Void> updateFinancialTransaction(@RequestBody @Validated FinancialTransactionUpdateDTO dto) {
        FinancialTransaction financialTransaction = BeanUtil.copyProperties(dto, FinancialTransaction.class);
        financialAccountService.updateByTransaction(financialTransaction);
        boolean updated = financialTransactionService.updateById(financialTransaction);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除收支明细
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("financial:transaction:remove")
    public Result<Void> removeFinancialTransaction(@PathVariable int id) {
        boolean removed = financialTransactionService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}