package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.MaterialWarehouseSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.MaterialWarehouseUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.MaterialWarehouse;
import com.github.lingkai5wu.loveta.model.vo.MaterialWarehouseBasicVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialWarehouseVO;
import com.github.lingkai5wu.loveta.service.IMaterialWarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资仓库
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/material-warehouses")
@AllArgsConstructor
public class MaterialWarehouseController {

    private final IMaterialWarehouseService materialWarehouseService;

    /**
     * 获取物资仓库
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:warehouse:get")
    public Result<MaterialWarehouseVO> getMaterialWarehouseVO(@PathVariable int id) {
        MaterialWarehouse materialWarehouse = materialWarehouseService.getById(id);
        if (materialWarehouse == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MaterialWarehouseVO materialWarehouseVO = BeanUtil.copyProperties(materialWarehouse, MaterialWarehouseVO.class);
        return Result.data(materialWarehouseVO);
    }

    /**
     * 列出全部物资仓库基本信息
     */
    @GetMapping
    @SaCheckPermission("material:warehouse:list")
    public Result<List<MaterialWarehouseBasicVO>> listMaterialWarehouseBasicVOs() {
        List<MaterialWarehouseBasicVO> materialWarehouseBasicVOList = materialWarehouseService.listMaterialWarehouseBasicVOs();
        return Result.data(materialWarehouseBasicVOList);
    }

    /**
     * 新增物资仓库
     */
    @PostMapping
    @SaCheckPermission("material:warehouse:save")
    public Result<Void> saveMaterialWarehouse(@RequestBody @Validated MaterialWarehouseSaveDTO dto) {
        MaterialWarehouse materialWarehouse = BeanUtil.copyProperties(dto, MaterialWarehouse.class);
        materialWarehouseService.save(materialWarehouse);
        return Result.ok();
    }

    /**
     * 修改物资仓库
     */
    @PutMapping
    @SaCheckPermission("material:warehouse:update")
    public Result<Void> updateMaterialWarehouse(@RequestBody @Validated MaterialWarehouseUpdateDTO dto) {
        MaterialWarehouse materialWarehouse = BeanUtil.copyProperties(dto, MaterialWarehouse.class);
        boolean updated = materialWarehouseService.updateById(materialWarehouse);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除物资仓库
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:warehouse:remove")
    public Result<Void> removeMaterialWarehouse(@PathVariable int id) {
        boolean removed = materialWarehouseService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
