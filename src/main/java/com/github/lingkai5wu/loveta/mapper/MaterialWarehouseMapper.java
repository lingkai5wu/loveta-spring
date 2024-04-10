package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialWarehouse;
import com.github.lingkai5wu.loveta.model.vo.MaterialWarehouseBasicVO;

import java.util.List;

/**
 * 物资仓库 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface MaterialWarehouseMapper extends BaseMapper<MaterialWarehouse> {

    List<MaterialWarehouseBasicVO> listMaterialWarehouseBasicVOs();
}
