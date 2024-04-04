package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Population;
import com.github.lingkai5wu.loveta.model.vo.PopulationBasicVO;

import java.util.List;

/**
 * 种群 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
public interface IPopulationService extends IService<Population> {

    List<PopulationBasicVO> listPopulationBasicVOs();
}
