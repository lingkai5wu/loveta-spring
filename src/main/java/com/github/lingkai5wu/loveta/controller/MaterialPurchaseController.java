package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.MaterialPurchaseSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.MaterialPurchaseUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.MaterialPurchase;
import com.github.lingkai5wu.loveta.model.vo.MaterialPurchaseBasicVO;
import com.github.lingkai5wu.loveta.model.vo.MaterialPurchaseVO;
import com.github.lingkai5wu.loveta.service.IMaterialPurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资采购记录
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/material-purchases")
@AllArgsConstructor
public class MaterialPurchaseController {

    private final IMaterialPurchaseService materialPurchaseService;

    /**
     * 获取物资采购记录
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:purchase:get")
    public Result<MaterialPurchaseVO> getMaterialPurchaseVO(@PathVariable int id) {
        MaterialPurchase materialPurchase = materialPurchaseService.getById(id);
        if (materialPurchase == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        MaterialPurchaseVO materialPurchaseVO = BeanUtil.copyProperties(materialPurchase, MaterialPurchaseVO.class);
        return Result.data(materialPurchaseVO);
    }

    /**
     * 列出全部物资采购记录基本信息
     */
    @GetMapping
    @SaCheckPermission("material:purchase:list")
    public Result<List<MaterialPurchaseBasicVO>> listMaterialPurchaseBasicVOs() {
        List<MaterialPurchase> materialPurchases = materialPurchaseService.list();
        List<MaterialPurchaseBasicVO> materialPurchaseBasicVOList = BeanUtil.copyToList(materialPurchases,
                MaterialPurchaseBasicVO.class);
        return Result.data(materialPurchaseBasicVOList);
    }

    /**
     * 新增物资采购记录
     */
    @PostMapping
    @SaCheckPermission("material:purchase:save")
    public Result<Void> saveMaterialPurchase(@RequestBody @Validated MaterialPurchaseSaveDTO dto) {
        MaterialPurchase materialPurchase = BeanUtil.copyProperties(dto, MaterialPurchase.class);
        materialPurchaseService.save(materialPurchase);
        return Result.ok();
    }

    /**
     * 修改物资采购记录
     */
    @PutMapping
    @SaCheckPermission("material:purchase:update")
    public Result<Void> updateMaterialPurchase(@RequestBody @Validated MaterialPurchaseUpdateDTO dto) {
        MaterialPurchase materialPurchase = BeanUtil.copyProperties(dto, MaterialPurchase.class);
        boolean updated = materialPurchaseService.updateById(materialPurchase);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除物资采购记录
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:purchase:remove")
    public Result<Void> removeMaterialPurchase(@PathVariable int id) {
        boolean removed = materialPurchaseService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
