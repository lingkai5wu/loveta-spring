package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MaterialWarehouseMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialWarehouse;
import com.github.lingkai5wu.loveta.model.vo.MaterialWarehouseBasicVO;
import com.github.lingkai5wu.loveta.service.IMaterialWarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资仓库 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class MaterialWarehouseServiceImpl extends ServiceImpl<MaterialWarehouseMapper, MaterialWarehouse> implements IMaterialWarehouseService {

    @Override
    public List<MaterialWarehouseBasicVO> listMaterialWarehouseBasicVOs() {
        return baseMapper.listMaterialWarehouseBasicVOs();
    }
}
