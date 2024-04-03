package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.AreaSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.AreaUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Area;
import com.github.lingkai5wu.loveta.model.po.Location;
import com.github.lingkai5wu.loveta.model.vo.AreaVO;
import com.github.lingkai5wu.loveta.service.IAreaService;
import com.github.lingkai5wu.loveta.service.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@RestController
@RequestMapping("/areas")
@AllArgsConstructor
public class AreaController {
    private final IAreaService areaService;
    private final ILocationService locationService;

    /**
     * 获取区域
     */
    @GetMapping("/{id}")
    @SaCheckPermission("area:get")
    public Result<AreaVO> getAreaVO(@PathVariable int id) {
        AreaVO areaVO = areaService.getAreaVO(id);
        if (areaVO == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.data(areaVO);
    }

    /**
     * 列出全部区域
     */
    @GetMapping
    @SaCheckPermission("area:list")
    public Result<List<AreaVO>> listAreaVOs() {
        List<AreaVO> areaVOList = areaService.listAreaVOs();
        return Result.data(areaVOList);
    }

    /**
     * 新增区域
     */
    @PostMapping
    @SaCheckPermission("area:save")
    @Transactional
    public Result<Void> saveArea(@RequestBody @Validated AreaSaveDTO dto) {
        Area area = BeanUtil.copyProperties(dto, Area.class);
        areaService.save(area);
        List<Location> locationList = BeanUtil.copyToList(dto.getLocations(), Location.class);
        if (locationList != null) {
            locationList.forEach(location -> location.setAreaId(area.getId()));
            locationService.saveBatch(locationList);
        }
        return Result.ok();
    }

    /**
     * 修改区域
     */
    @PutMapping
    @SaCheckPermission("area:update")
    @Transactional
    public Result<Void> updateArea(@RequestBody @Validated AreaUpdateDTO dto) {
        Area area = BeanUtil.copyProperties(dto, Area.class);
        boolean updated = areaService.updateById(area);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        List<Location> locationList = BeanUtil.copyToList(dto.getLocations(), Location.class);
        if (locationList != null) {
            locationService.saveOrUpdateBatch(locationList);
        }
        return Result.ok();
    }

    /**
     * 删除区域
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("area:remove")
    public Result<Void> removeArea(@PathVariable int id) {
        boolean removed = areaService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
