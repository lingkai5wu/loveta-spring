package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.vo.MaterialMovementBasicVO;

import java.util.List;

/**
 * 物资出入库记录 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface MaterialMovementMapper extends BaseMapper<MaterialMovement> {

    List<MaterialMovementBasicVO> listMaterialMovementBasicVOs();
}
