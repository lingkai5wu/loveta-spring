package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.DonationMapper;
import com.github.lingkai5wu.loveta.model.po.Donation;
import com.github.lingkai5wu.loveta.model.vo.DonationBasicVO;
import com.github.lingkai5wu.loveta.service.IDonationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 捐赠 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class DonationServiceImpl extends ServiceImpl<DonationMapper, Donation> implements IDonationService {

    @Override
    public List<DonationBasicVO> listDonationBasicVOs() {
        return baseMapper.listDonationBasicVOs();
    }
}
