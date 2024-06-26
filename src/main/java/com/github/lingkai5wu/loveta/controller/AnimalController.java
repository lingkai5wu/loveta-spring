package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lingkai5wu.loveta.model.PageDTO;
import com.github.lingkai5wu.loveta.model.PageVO;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.AnimalSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.AnimalUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Animal;
import com.github.lingkai5wu.loveta.model.query.AnimalQuery;
import com.github.lingkai5wu.loveta.model.vo.AnimalBasicVO;
import com.github.lingkai5wu.loveta.model.vo.AnimalVO;
import com.github.lingkai5wu.loveta.service.IAnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动物
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@RestController
@RequestMapping("/animals")
@AllArgsConstructor
public class AnimalController {

    private final IAnimalService animalService;

    /**
     * 获取动物
     */
    @GetMapping("/{id}")
    @SaCheckPermission("animal:get")
    public Result<AnimalVO> getAnimalVO(@PathVariable int id) {
        Animal animal = animalService.getById(id);
        if (animal == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        AnimalVO animalVO = BeanUtil.copyProperties(animal, AnimalVO.class);
        return Result.data(animalVO);
    }

    /**
     * 列出全部动物基本信息
     */
    @GetMapping
    @SaCheckPermission("animal:list")
    public Result<List<AnimalBasicVO>> listAnimalBasicVOs(AnimalQuery query) {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>(BeanUtil.copyProperties(query, Animal.class));
        List<AnimalBasicVO> animalBasicVOList = BeanUtil.copyToList(animalService.list(wrapper), AnimalBasicVO.class);
        return Result.data(animalBasicVOList);
    }

    /**
     * 分页列出全部动物基本信息
     */
    @GetMapping("/page")
    @SaCheckPermission("animal:page")
    public Result<PageVO<AnimalBasicVO>> listAnimalBasicVOsWithPage(AnimalQuery query, PageDTO pageDTO) {
        Page<Animal> page = new Page<>();
        BeanUtil.copyProperties(pageDTO, page, new CopyOptions().ignoreNullValue());
        QueryWrapper<Animal> wrapper = new QueryWrapper<>(BeanUtil.copyProperties(query, Animal.class));
        page = animalService.page(page, wrapper);
        List<AnimalBasicVO> animalBasicVOList = BeanUtil.copyToList(page.getRecords(), AnimalBasicVO.class);
        return Result.page(animalBasicVOList, page);
    }

    /**
     * 新增动物
     */
    @PostMapping
    @SaCheckPermission("animal:save")
    public Result<Void> saveAnimal(@RequestBody @Validated AnimalSaveDTO dto) {
        Animal animal = BeanUtil.copyProperties(dto, Animal.class);
        animalService.save(animal);
        return Result.ok();
    }

    /**
     * 修改动物
     */
    @PutMapping
    @SaCheckPermission("animal:update")
    public Result<Void> updateAnimal(@RequestBody @Validated AnimalUpdateDTO dto) {
        Animal animal = BeanUtil.copyProperties(dto, Animal.class);
        boolean updated = animalService.updateById(animal);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除动物
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("animal:remove")
    public Result<Void> removeAnimal(@PathVariable int id) {
        boolean removed = animalService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
