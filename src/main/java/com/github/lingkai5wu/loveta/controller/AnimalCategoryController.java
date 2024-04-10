package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.CategorySaveDTO;
import com.github.lingkai5wu.loveta.model.dto.CategoryUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.AnimalCategory;
import com.github.lingkai5wu.loveta.model.vo.CategoryVO;
import com.github.lingkai5wu.loveta.service.IAnimalCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动物分类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@RestController
@RequestMapping("/animal-categories")
@AllArgsConstructor
public class AnimalCategoryController {

    private final IAnimalCategoryService categoryService;

    /**
     * 获取分类
     */
    @GetMapping("/{id}")
    @SaCheckPermission("animal:category:get")
    public Result<CategoryVO> getCategoryVO(@PathVariable int id) {
        AnimalCategory animalCategory = categoryService.getById(id);
        if (animalCategory == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        CategoryVO categoryVO = BeanUtil.copyProperties(animalCategory, CategoryVO.class);
        return Result.data(categoryVO);
    }

    /**
     * 列出全部分类
     */
    @GetMapping
    @SaCheckPermission("animal:category:list")
    public Result<List<CategoryVO>> listCategoryVOs() {
        List<CategoryVO> categoryVOList = BeanUtil.copyToList(categoryService.list(), CategoryVO.class);
        return Result.data(categoryVOList);
    }

    /**
     * 新增分类
     */
    @PostMapping
    @SaCheckPermission("animal:category:save")
    public Result<Void> saveCategory(@RequestBody @Validated CategorySaveDTO dto) {
        AnimalCategory animalCategory = BeanUtil.copyProperties(dto, AnimalCategory.class);
        categoryService.save(animalCategory);
        return Result.ok();
    }

    /**
     * 修改分类
     */
    @PutMapping
    @SaCheckPermission("animal:category:update")
    public Result<Void> updateCategory(@RequestBody @Validated CategoryUpdateDTO dto) {
        AnimalCategory animalCategory = BeanUtil.copyProperties(dto, AnimalCategory.class);
        boolean updated = categoryService.updateById(animalCategory);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("animal:category:remove")
    public Result<Void> removeCategory(@PathVariable int id) {
        boolean removed = categoryService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
