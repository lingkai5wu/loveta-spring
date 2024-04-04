package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Population;
import com.github.lingkai5wu.loveta.model.vo.PopulationBasicVO;

import java.util.List;

/**
 * 种群 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
public interface PopulationMapper extends BaseMapper<Population> {

    List<PopulationBasicVO> listPopulationBasicVOs();
}
