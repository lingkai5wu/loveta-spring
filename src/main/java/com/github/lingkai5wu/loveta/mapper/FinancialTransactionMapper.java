package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.FinancialTransaction;
import com.github.lingkai5wu.loveta.model.vo.FinancialTransactionBasicVO;

import java.util.List;

/**
 * 收支明细 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface FinancialTransactionMapper extends BaseMapper<FinancialTransaction> {

    List<FinancialTransactionBasicVO> listFinancialTransactionBasicVOs();
}
