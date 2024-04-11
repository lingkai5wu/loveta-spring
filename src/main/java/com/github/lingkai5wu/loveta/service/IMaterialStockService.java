package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockBasicVO;

import java.util.List;

/**
 * 物资库存 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IMaterialStockService extends IService<MaterialStock> {

    List<MaterialStockBasicVO> listMaterialStockBasicVOs();

    List<MaterialStock> listByMaterialId(int id);

    boolean updateByMovement(MaterialMovement materialMovement);
}
