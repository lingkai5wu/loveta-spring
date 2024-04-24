package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MaterialStockMapper;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockBasicVO;
import com.github.lingkai5wu.loveta.service.IMaterialMovementService;
import com.github.lingkai5wu.loveta.service.IMaterialStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资库存 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@Service
@AllArgsConstructor
public class MaterialStockServiceImpl extends ServiceImpl<MaterialStockMapper, MaterialStock> implements IMaterialStockService {

    private final IMaterialMovementService materialMovementService;

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
    public boolean updateByMovement(MaterialMovement currentMovement) {
        if (currentMovement.getQuantity() == null) {
            return false;
        }
        int currentQuantity;
        if (currentMovement.getId() == null) {
            MaterialStock currentStock = getById(currentMovement.getStockId());
            currentQuantity = currentStock.getQuantity() + currentMovement.getQuantity();
        } else {
            MaterialMovement materialMovement = materialMovementService.getById(currentMovement.getId());
            MaterialStock currentStock = getById(materialMovement.getStockId());
            currentQuantity =
                    currentStock.getQuantity() - materialMovement.getQuantity() + currentMovement.getQuantity();
            if (currentMovement.getStockId() == null) {
                currentMovement.setStockId(currentStock.getId());
            }
        }
        if (currentQuantity < 0) {
            throw new IllegalArgumentException("库存不足");
        }
        return updateById(new MaterialStock()
                .setId(currentMovement.getStockId())
                .setQuantity(currentQuantity)
        );
    }
}
