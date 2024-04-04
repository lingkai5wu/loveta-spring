package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.PopulationSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.PopulationUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Population;
import com.github.lingkai5wu.loveta.model.vo.PopulationInfoVO;
import com.github.lingkai5wu.loveta.model.vo.PopulationVO;
import com.github.lingkai5wu.loveta.service.IPopulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 种群
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@RestController
@RequestMapping("/populations")
@AllArgsConstructor
public class PopulationController {

    private final IPopulationService populationService;

    /**
     * 获取种群
     */
    @GetMapping("/{id}")
    @SaCheckPermission("population:get")
    public Result<PopulationVO> getPopulationVO(@PathVariable int id) {
        Population population = populationService.getById(id);
        if (population == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        PopulationVO populationVO = BeanUtil.copyProperties(population, PopulationVO.class);
        return Result.data(populationVO);
    }

    /**
     * 列出全部种群
     */
    @GetMapping
    @SaCheckPermission("population:list")
    public Result<List<PopulationInfoVO>> listPopulationInfoVOs() {
        List<PopulationInfoVO> populationInfoVOList = populationService.listPopulationInfoVOs();
        return Result.data(populationInfoVOList);
    }

    /**
     * 新增种群
     */
    @PostMapping
    @SaCheckPermission("population:save")
    public Result<Void> savePopulation(@RequestBody @Validated PopulationSaveDTO dto) {
        Population population = BeanUtil.copyProperties(dto, Population.class);
        populationService.save(population);
        return Result.ok();
    }

    /**
     * 修改种群
     */
    @PutMapping
    @SaCheckPermission("population:update")
    public Result<Void> updatePopulation(@RequestBody @Validated PopulationUpdateDTO dto) {
        Population population = BeanUtil.copyProperties(dto, Population.class);
        boolean updated = populationService.updateById(population);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除种群
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("population:remove")
    public Result<Void> removePopulation(@PathVariable int id) {
        boolean removed = populationService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
