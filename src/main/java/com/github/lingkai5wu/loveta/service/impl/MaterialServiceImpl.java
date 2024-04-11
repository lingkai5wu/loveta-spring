package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MaterialMapper;
import com.github.lingkai5wu.loveta.model.po.Material;
import com.github.lingkai5wu.loveta.model.vo.MaterialBasicVO;
import com.github.lingkai5wu.loveta.service.IMaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Override
    public List<MaterialBasicVO> listMaterialBasicVOs() {
        return baseMapper.listMaterialBasicVOs();
    }
}
