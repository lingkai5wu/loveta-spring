package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Material;
import com.github.lingkai5wu.loveta.model.vo.MaterialBasicVO;

import java.util.List;

/**
 * 物资 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IMaterialService extends IService<Material> {

    List<MaterialBasicVO> listMaterialBasicVOs();
}
