package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.MaterialSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.MaterialUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Material;
import com.github.lingkai5wu.loveta.model.po.MaterialStock;
import com.github.lingkai5wu.loveta.model.vo.MaterialBasicVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialStockVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialVO;
import com.github.lingkai5wu.loveta.service.IMaterialMovementService;
import com.github.lingkai5wu.loveta.service.IMaterialService;
import com.github.lingkai5wu.loveta.service.IMaterialStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/materials")
@AllArgsConstructor
public class MaterialController {

    private final IMaterialService materialService;
    private final IMaterialStockService materialStockService;
    private final IMaterialMovementService materialMovementService;

    /**
     * 获取物资
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:get")
    public Result<MaterialVO> getMaterialVO(@PathVariable int id) {
        Material material = materialService.getById(id);
        if (material == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MaterialVO materialVO = BeanUtil.copyProperties(material, MaterialVO.class);
        return Result.data(materialVO);
    }

    /**
     * 列出全部物资基本信息
     */
    @GetMapping
    @SaCheckPermission("material:list")
    public Result<List<MaterialBasicVO>> listMaterialBasicVOs() {
        List<MaterialBasicVO> materialBasicVOList = materialService.listMaterialBasicVOs();
        return Result.data(materialBasicVOList);
    }

    /**
     * 列出物资库存
     */
    @GetMapping("/{id}/stock")
    @SaCheckPermission("material:stock:list")
    public Result<List<MaterialStockVO>> getMaterialStockVO(@PathVariable int id) {
        List<MaterialStock> materialStock = materialStockService.listByMaterialId(id);
        if (materialStock == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<MaterialStockVO> materialStockVOList = BeanUtil.copyToList(materialStock, MaterialStockVO.class);
        return Result.data(materialStockVOList);
    }

    /**
     * 新增物资
     */
    @PostMapping
    @SaCheckPermission("material:save")
    public Result<Void> saveMaterial(@RequestBody @Validated MaterialSaveDTO dto) {
        Material material = BeanUtil.copyProperties(dto, Material.class);
        materialService.save(material);
        return Result.ok();
    }

    /**
     * 修改物资
     */
    @PutMapping
    @SaCheckPermission("material:update")
    public Result<Void> updateMaterial(@RequestBody @Validated MaterialUpdateDTO dto) {
        Material material = BeanUtil.copyProperties(dto, Material.class);
        boolean updated = materialService.updateById(material);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除物资
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:remove")
    public Result<Void> removeMaterial(@PathVariable int id) {
        boolean removed = materialService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
