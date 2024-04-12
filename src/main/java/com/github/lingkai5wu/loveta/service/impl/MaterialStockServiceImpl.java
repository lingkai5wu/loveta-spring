package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MaterialStockMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockBasicVO;
import com.github.lingkai5wu.loveta.service.IMaterialStockService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资库存 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
public class MaterialStockServiceImpl extends ServiceImpl<MaterialStockMapper, MaterialStock> implements IMaterialStockService {

    @Override
    public List<MaterialStockBasicVO> listMaterialStockBasicVOs() {
        return baseMapper.listMaterialStockBasicVOs();
    }

    @Override
    public List<MaterialStock> listByMaterialId(int id) {
        return lambdaQuery()
                .eq(MaterialStock::getMaterialId, id)
                .list();
    }

    @Override
    public boolean updateByMovement(MaterialMovement materialMovement) {
        if (materialMovement.getStatus() == null ||
                materialMovement.getStatus() != EntityStatusEnum.APPROVED) {
            return false;
        }
        Integer stockId = materialMovement.getStockId();
        MaterialStock materialStock = getById(stockId);
        int current = materialStock.getCurrent() + materialMovement.getQuantity();
        if (current < 0) {
            throw new IllegalArgumentException("库存不足");
        }
        return updateById(new MaterialStock()
                .setId(stockId)
                .setCurrent(current)
        );
    }
}
