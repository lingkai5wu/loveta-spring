package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockBasicVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockVO;
import com.github.lingkai5wu.loveta.service.IMaterialStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资库存
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/material-stocks")
@AllArgsConstructor
public class MaterialStockController {

    private final IMaterialStockService materialStockService;

    /**
     * 获取库存
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:stock:get")
    public Result<MaterialStockVO> getMaterialStockVO(@PathVariable int id) {
        MaterialStock materialMaterialStock = materialStockService.getById(id);
        if (materialMaterialStock == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MaterialStockVO materialStockVO = BeanUtil.copyProperties(materialMaterialStock, MaterialStockVO.class);
        return Result.data(materialStockVO);
    }

    /**
     * 列出全部库存
     */
    @GetMapping
    @SaCheckPermission("material:stock:list")
    public Result<List<MaterialStockBasicVO>> listMaterialStockBasicVOs() {
        List<MaterialStockBasicVO> materialStockBasicVOList = materialStockService.listMaterialStockBasicVOs();
        return Result.data(materialStockBasicVOList);
    }

    /**
     * 删除库存
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:stock:remove")
    public Result<Void> removeMaterialStock(@PathVariable int id) {
        MaterialStock materialStock = materialStockService.getById(id);
        if (materialStock.getQuantity() > 0) {
            return Result.error("库存数量不为0，无法删除");
        }
        boolean removed = materialStockService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
