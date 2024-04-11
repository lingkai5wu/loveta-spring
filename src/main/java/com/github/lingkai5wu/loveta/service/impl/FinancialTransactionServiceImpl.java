package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.FinancialTransactionMapper;
import com.github.lingkai5wu.loveta.model.po.FinancialTransaction;
import com.github.lingkai5wu.loveta.model.vo.FinancialTransactionBasicVO;
import com.github.lingkai5wu.loveta.service.IFinancialTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收支明细 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class FinancialTransactionServiceImpl extends ServiceImpl<FinancialTransactionMapper, FinancialTransaction> implements IFinancialTransactionService {

    @Override
    public List<FinancialTransactionBasicVO> listFinancialTransactionBasicVOs() {
        return baseMapper.listFinancialTransactionBasicVOs();
    }
}
