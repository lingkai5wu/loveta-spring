package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.FinancialAccountSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.FinancialAccountUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.FinancialAccount;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountBasicVO;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountVO;
import com.github.lingkai5wu.loveta.service.IFinancialAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资金账户
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/financial-accounts")
@AllArgsConstructor
public class FinancialAccountController {

    private final IFinancialAccountService financialAccountService;

    /**
     * 获取资金账户
     */
    @GetMapping("/{id}")
    @SaCheckPermission("financial:account:get")
    public Result<FinancialAccountVO> getFinancialAccountVO(@PathVariable int id) {
        FinancialAccount financialAccount = financialAccountService.getById(id);
        if (financialAccount == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        FinancialAccountVO financialAccountVO = BeanUtil.copyProperties(financialAccount, FinancialAccountVO.class);
        return Result.data(financialAccountVO);
    }

    /**
     * 列出全部资金账户基本信息
     */
    @GetMapping
    @SaCheckPermission("financial:account:list")
    public Result<List<FinancialAccountBasicVO>> listFinancialAccountBasicVOs() {
        List<FinancialAccountBasicVO> financialAccountBasicVOList = financialAccountService.listFinancialAccountBasicVOs();
        return Result.data(financialAccountBasicVOList);
    }

    /**
     * 新增资金账户
     */
    @PostMapping
    @SaCheckPermission("financial:account:save")
    public Result<Void> saveFinancialAccount(@RequestBody @Validated FinancialAccountSaveDTO dto) {
        FinancialAccount financialAccount = BeanUtil.copyProperties(dto, FinancialAccount.class);
        financialAccountService.save(financialAccount);
        return Result.ok();
    }

    /**
     * 修改资金账户
     */
    @PutMapping
    @SaCheckPermission("financial:account:update")
    public Result<Void> updateFinancialAccount(@RequestBody @Validated FinancialAccountUpdateDTO dto) {
        FinancialAccount financialAccount = BeanUtil.copyProperties(dto, FinancialAccount.class);
        boolean updated = financialAccountService.updateById(financialAccount);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除资金账户
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("financial:account:remove")
    public Result<Void> removeFinancialAccount(@PathVariable int id) {
        boolean removed = financialAccountService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}