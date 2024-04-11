package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.FinancialAccount;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountBasicVO;

import java.util.List;

/**
 * 资金账户 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IFinancialAccountService extends IService<FinancialAccount> {

    List<FinancialAccountBasicVO> listFinancialAccountBasicVOs();
}
