package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Material;
import com.github.lingkai5wu.loveta.model.vo.MaterialBasicVO;

import java.util.List;

/**
 * 物资 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface MaterialMapper extends BaseMapper<Material> {

    List<MaterialBasicVO> listMaterialBasicVOs();
}
