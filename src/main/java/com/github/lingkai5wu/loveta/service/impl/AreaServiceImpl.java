package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.AreaMapper;
import com.github.lingkai5wu.loveta.model.po.Area;
import com.github.lingkai5wu.loveta.model.vo.AreaVO;
import com.github.lingkai5wu.loveta.service.IAreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Override
    public AreaVO getAreaVO(int id) {
        return baseMapper.getAreaVO(id);
    }

    @Override
    public List<AreaVO> listAreaVOs() {
        return baseMapper.listAreaVOs();
    }
}
