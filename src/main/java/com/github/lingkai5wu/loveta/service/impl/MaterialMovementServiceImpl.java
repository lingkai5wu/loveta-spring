package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MaterialMovementMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.vo.MaterialMovementBasicVO;
import com.github.lingkai5wu.loveta.service.IMaterialMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资出入库记录 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class MaterialMovementServiceImpl extends ServiceImpl<MaterialMovementMapper, MaterialMovement> implements IMaterialMovementService {

    @Override
    public List<MaterialMovementBasicVO> listMaterialMovementBasicVOs() {
        return baseMapper.listMaterialMovementBasicVOs();
    }
}
