package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.PopulationMapper;
import com.github.lingkai5wu.loveta.model.po.Population;
import com.github.lingkai5wu.loveta.model.vo.PopulationInfoVO;
import com.github.lingkai5wu.loveta.service.IPopulationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 种群 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Service
public class PopulationServiceImpl extends ServiceImpl<PopulationMapper, Population> implements IPopulationService {

    @Override
    public List<PopulationInfoVO> listPopulationInfoVOs() {
        return baseMapper.listPopulationInfoVOs();
    }
}
