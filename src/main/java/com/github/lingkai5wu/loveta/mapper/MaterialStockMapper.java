package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockBasicVO;

import java.util.List;

/**
 * 物资库存 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface MaterialStockMapper extends BaseMapper<MaterialStock> {

    List<MaterialStockBasicVO> listMaterialStockBasicVOs();
}
