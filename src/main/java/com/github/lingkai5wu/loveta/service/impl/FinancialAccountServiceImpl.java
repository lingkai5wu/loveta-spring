package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.FinancialAccountMapper;
import com.github.lingkai5wu.loveta.model.po.FinancialAccount;
import com.github.lingkai5wu.loveta.model.po.FinancialTransaction;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountBasicVO;
import com.github.lingkai5wu.loveta.service.IFinancialAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金账户 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
@AllArgsConstructor
public class FinancialAccountServiceImpl extends ServiceImpl<FinancialAccountMapper, FinancialAccount> implements IFinancialAccountService {

    private final FinancialTransactionServiceImpl financialTransactionService;

    @Override
    public List<FinancialAccountBasicVO> listFinancialAccountBasicVOs() {
        return baseMapper.listFinancialAccountBasicVOs();
    }

    @Override
    public boolean updateByTransaction(FinancialTransaction financialTransaction) {
        if (financialTransaction.getAmount() == null) {
            return false;
        }
        int currentBalance;
        if (financialTransaction.getId() == null) {
            FinancialAccount currentAccount = getById(financialTransaction.getAccountId());
            currentBalance = currentAccount.getBalance() + financialTransaction.getAmount();
        } else {
            FinancialTransaction currentTransaction = financialTransactionService.getById(financialTransaction.getId());
            FinancialAccount currentAccount = getById(currentTransaction.getAccountId());
            currentBalance = currentAccount.getBalance() - financialTransaction.getAmount() + currentTransaction.getAmount();
            if (financialTransaction.getAccountId() == null) {
                financialTransaction.setAccountId(currentAccount.getId());
            }
        }
        return updateById(new FinancialAccount()
                .setId(financialTransaction.getAccountId())
                .setBalance(currentBalance));
    }
}
