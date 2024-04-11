package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.FinancialAccountMapper;
import com.github.lingkai5wu.loveta.model.po.FinancialAccount;
import com.github.lingkai5wu.loveta.model.vo.FinancialAccountBasicVO;
import com.github.lingkai5wu.loveta.service.IFinancialAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金账户 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class FinancialAccountServiceImpl extends ServiceImpl<FinancialAccountMapper, FinancialAccount> implements IFinancialAccountService {

    @Override
    public List<FinancialAccountBasicVO> listFinancialAccountBasicVOs() {
        return baseMapper.listFinancialAccountBasicVOs();
    }
}
