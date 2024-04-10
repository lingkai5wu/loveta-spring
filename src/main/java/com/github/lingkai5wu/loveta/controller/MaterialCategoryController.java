package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.CategorySaveDTO;
import com.github.lingkai5wu.loveta.model.dto.CategoryUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.MaterialCategory;
import com.github.lingkai5wu.loveta.model.vo.CategoryVO;
import com.github.lingkai5wu.loveta.service.IMaterialCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资分类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/material-categories")
@AllArgsConstructor
public class MaterialCategoryController {

    private final IMaterialCategoryService categoryService;

    /**
     * 获取分类
     */
    @GetMapping("/{id}")
    @SaCheckPermission("material:category:get")
    public Result<CategoryVO> getCategoryVO(@PathVariable int id) {
        MaterialCategory materialCategory = categoryService.getById(id);
        if (materialCategory == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        CategoryVO categoryVO = BeanUtil.copyProperties(materialCategory, CategoryVO.class);
        return Result.data(categoryVO);
    }

    /**
     * 列出全部分类
     */
    @GetMapping
    @SaCheckPermission("material:category:list")
    public Result<List<CategoryVO>> listCategoryVOs() {
        List<CategoryVO> categoryVOList = BeanUtil.copyToList(categoryService.list(), CategoryVO.class);
        return Result.data(categoryVOList);
    }

    /**
     * 新增分类
     */
    @PostMapping
    @SaCheckPermission("material:category:save")
    public Result<Void> saveCategory(@RequestBody @Validated CategorySaveDTO dto) {
        MaterialCategory materialCategory = BeanUtil.copyProperties(dto, MaterialCategory.class);
        categoryService.save(materialCategory);
        return Result.ok();
    }

    /**
     * 修改分类
     */
    @PutMapping
    @SaCheckPermission("material:category:update")
    public Result<Void> updateCategory(@RequestBody @Validated CategoryUpdateDTO dto) {
        MaterialCategory materialCategory = BeanUtil.copyProperties(dto, MaterialCategory.class);
        boolean updated = categoryService.updateById(materialCategory);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("material:category:remove")
    public Result<Void> removeCategory(@PathVariable int id) {
        boolean removed = categoryService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
