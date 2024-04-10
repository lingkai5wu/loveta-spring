package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.CategorySaveDTO;
import com.github.lingkai5wu.loveta.model.dto.CategoryUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.FinancialTransactionCategory;
import com.github.lingkai5wu.loveta.model.vo.CategoryVO;
import com.github.lingkai5wu.loveta.service.IFinancialTransactionCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收支分类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/financial-transaction-categories")
@AllArgsConstructor
public class FinancialTransactionCategoryController {

    private final IFinancialTransactionCategoryService categoryService;

    /**
     * 获取分类
     */
    @GetMapping("/{id}")
    @SaCheckPermission("financial:transaction:category:get")
    public Result<CategoryVO> getCategoryVO(@PathVariable int id) {
        FinancialTransactionCategory financialTransactionCategory = categoryService.getById(id);
        if (financialTransactionCategory == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        CategoryVO categoryVO = BeanUtil.copyProperties(financialTransactionCategory, CategoryVO.class);
        return Result.data(categoryVO);
    }

    /**
     * 列出全部分类
     */
    @GetMapping
    @SaCheckPermission("financial:transaction:category:list")
    public Result<List<CategoryVO>> listCategoryVOs() {
        List<CategoryVO> categoryVOList = BeanUtil.copyToList(categoryService.list(), CategoryVO.class);
        return Result.data(categoryVOList);
    }

    /**
     * 新增分类
     */
    @PostMapping
    @SaCheckPermission("financial:transaction:category:save")
    public Result<Void> saveCategory(@RequestBody @Validated CategorySaveDTO dto) {
        FinancialTransactionCategory financialTransactionCategory = BeanUtil.copyProperties(dto, FinancialTransactionCategory.class);
        categoryService.save(financialTransactionCategory);
        return Result.ok();
    }

    /**
     * 修改分类
     */
    @PutMapping
    @SaCheckPermission("financial:transaction:category:update")
    public Result<Void> updateCategory(@RequestBody @Validated CategoryUpdateDTO dto) {
        FinancialTransactionCategory financialTransactionCategory = BeanUtil.copyProperties(dto, FinancialTransactionCategory.class);
        boolean updated = categoryService.updateById(financialTransactionCategory);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("financial:transaction:category:remove")
    public Result<Void> removeCategory(@PathVariable int id) {
        boolean removed = categoryService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
