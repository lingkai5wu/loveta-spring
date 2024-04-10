package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.SpeciesMapper;
import com.github.lingkai5wu.loveta.model.po.Species;
import com.github.lingkai5wu.loveta.service.ISpeciesService;
import org.springframework.stereotype.Service;

/**
 * 物种 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Service
public class SpeciesServiceImpl extends ServiceImpl<SpeciesMapper, Species> implements ISpeciesService {
}