package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Area;
import com.github.lingkai5wu.loveta.model.vo.AreaVO;

import java.util.List;

/**
 * 区域 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
public interface AreaMapper extends BaseMapper<Area> {

    AreaVO getAreaVO(int id);

    List<AreaVO> listAreaVOs();
}
