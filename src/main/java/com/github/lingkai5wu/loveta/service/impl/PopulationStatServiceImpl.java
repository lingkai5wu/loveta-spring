package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.PopulationStatMapper;
import com.github.lingkai5wu.loveta.model.po.PopulationStat;
import com.github.lingkai5wu.loveta.service.IPopulationStatService;
import org.springframework.stereotype.Service;

/**
 * 种群统计信息 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Service
public class PopulationStatServiceImpl extends ServiceImpl<PopulationStatMapper, PopulationStat> implements IPopulationStatService {

}
