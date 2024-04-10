package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.MaterialWarehouse;
import com.github.lingkai5wu.loveta.model.vo.MaterialWarehouseBasicVO;

import java.util.List;

/**
 * 物资仓库 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IMaterialWarehouseService extends IService<MaterialWarehouse> {

    List<MaterialWarehouseBasicVO> listMaterialWarehouseBasicVOs();
}
