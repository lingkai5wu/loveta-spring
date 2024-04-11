package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.FinancialAccount;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountBasicVO;

import java.util.List;

/**
 * 资金账户 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface FinancialAccountMapper extends BaseMapper<FinancialAccount> {

    List<FinancialAccountBasicVO> listFinancialAccountBasicVOs();
}
