package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.FinancialTransaction;
import com.github.lingkai5wu.loveta.model.vo.FinancialTransactionBasicVO;

import java.util.List;

/**
 * 收支明细 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IFinancialTransactionService extends IService<FinancialTransaction> {

    List<FinancialTransactionBasicVO> listFinancialTransactionBasicVOs();
}
