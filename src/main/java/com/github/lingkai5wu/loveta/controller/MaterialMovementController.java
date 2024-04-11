package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.MaterialMovementSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.MaterialMovementUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.MaterialMovement;
import com.github.lingkai5wu.loveta.model.vo.MaterialMovementBasicVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialMovementVO;
import com.github.lingkai5wu.loveta.service.IMaterialMovementService;
import com.github.lingkai5wu.loveta.service.IMaterialStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资出入库记录
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/material-movements")
@AllArgsConstructor
public class MaterialMovementController {
    private final IMaterialMovementService materialMovementService;
    private final IMaterialStockService materialStockService;

    /**
     * 获取物资出入库记录
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:movement:get")
    public Result<MaterialMovementVO> getMaterialMovementVO(@PathVariable int id) {
        MaterialMovement materialMovement = materialMovementService.getById(id);
        if (materialMovement == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MaterialMovementVO materialMovementVO = BeanUtil.copyProperties(materialMovement, MaterialMovementVO.class);
        return Result.data(materialMovementVO);
    }

    /**
     * 列出全部物资出入库记录基本信息
     */
    @GetMapping
    @SaCheckPermission("material:movement:list")
    public Result<List<MaterialMovementBasicVO>> listMaterialMovementBasicVOs() {
        List<MaterialMovementBasicVO> materialMovementBasicVOList = materialMovementService.listMaterialMovementBasicVOs();
        return Result.data(materialMovementBasicVOList);
    }

    /**
     * 新增物资出入库记录
     */
    @PostMapping
    @SaCheckPermission("material:movement:save")
    @Transactional
    public Result<Void> saveMaterialMovement(@RequestBody @Validated MaterialMovementSaveDTO dto) {
        MaterialMovement materialMovement = BeanUtil.copyProperties(dto, MaterialMovement.class);
        materialMovementService.save(materialMovement);
        materialStockService.updateByMovement(materialMovement);
        return Result.ok();
    }

    /**
     * 修改物资出入库记录
     */
    @PutMapping
    @SaCheckPermission("material:movement:update")
    @Transactional
    public Result<Void> updateMaterialMovement(@RequestBody @Validated MaterialMovementUpdateDTO dto) {
        MaterialMovement materialMovement = BeanUtil.copyProperties(dto, MaterialMovement.class);
        boolean updated = materialMovementService.updateById(materialMovement);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        materialStockService.updateByMovement(materialMovement);
        return Result.ok();
    }

    /**
     * 删除物资出入库记录
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:movement:remove")
    public Result<Void> removeMaterialMovement(@PathVariable int id) {
        boolean removed = materialMovementService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
