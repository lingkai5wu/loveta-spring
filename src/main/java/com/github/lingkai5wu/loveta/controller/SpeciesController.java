package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.SpeciesSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.SpeciesUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Species;
import com.github.lingkai5wu.loveta.model.vo.SpeciesVO;
import com.github.lingkai5wu.loveta.service.ISpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物种
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@RestController
@RequestMapping("/species")
@AllArgsConstructor
public class SpeciesController {

    private final ISpeciesService speciesService;

    /**
     * 获取物种
     */
    @GetMapping("/{id}")
    @SaCheckPermission("species:get")
    public Result<SpeciesVO> getSpeciesVO(@PathVariable int id) {
        Species species = speciesService.getById(id);
        if (species == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        SpeciesVO speciesVO = BeanUtil.copyProperties(species, SpeciesVO.class);
        return Result.data(speciesVO);
    }

    /**
     * 列出全部物种
     */
    @GetMapping
    @SaCheckPermission("species:list")
    public Result<List<SpeciesVO>> listSpeciesVOs() {
        List<SpeciesVO> speciesVOList = BeanUtil.copyToList(speciesService.list(), SpeciesVO.class);
        return Result.data(speciesVOList);
    }

    /**
     * 新增物种
     */
    @PostMapping
    @SaCheckPermission("species:save")
    public Result<Void> saveSpecies(@RequestBody @Validated SpeciesSaveDTO dto) {
        Species species = BeanUtil.copyProperties(dto, Species.class);
        speciesService.save(species);
        return Result.ok();
    }

    /**
     * 修改物种
     */
    @PutMapping
    @SaCheckPermission("species:update")
    public Result<Void> updateSpecies(@RequestBody @Validated SpeciesUpdateDTO dto) {
        Species species = BeanUtil.copyProperties(dto, Species.class);
        boolean updated = speciesService.updateById(species);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除物种
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("species:remove")
    public Result<Void> removeSpecies(@PathVariable int id) {
        boolean removed = speciesService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
