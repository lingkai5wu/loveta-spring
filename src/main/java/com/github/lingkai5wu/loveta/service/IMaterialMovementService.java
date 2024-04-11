package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.vo.MaterialMovementBasicVO;

import java.util.List;

/**
 * 物资出入库记录 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IMaterialMovementService extends IService<MaterialMovement> {

    List<MaterialMovementBasicVO> listMaterialMovementBasicVOs();
}
