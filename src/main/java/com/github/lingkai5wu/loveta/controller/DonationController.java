package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.DonationSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.DonationUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Donation;
import com.github.lingkai5wu.loveta.model.vo.DonationBasicVO;
import com.github.lingkai5wu.loveta.model.vo.DonationVO;
import com.github.lingkai5wu.loveta.service.IDonationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 捐赠
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/donations")
@AllArgsConstructor
public class DonationController {
    private final IDonationService donationService;

    /**
     * 获取捐赠
     */
    @GetMapping("/{id}")
    @SaCheckPermission("donation:get")
    public Result<DonationVO> getDonationVO(@PathVariable int id) {
        Donation donation = donationService.getById(id);
        if (donation == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        DonationVO donationVO = BeanUtil.copyProperties(donation, DonationVO.class);
        return Result.data(donationVO);
    }

    /**
     * 列出全部捐赠基本信息
     */
    @GetMapping
    @SaCheckPermission("donation:list")
    public Result<List<DonationBasicVO>> listDonationBasicVOs() {
        List<DonationBasicVO> donationBasicVOList = donationService.listDonationBasicVOs();
        return Result.data(donationBasicVOList);
    }

    /**
     * 新增捐赠
     */
    @PostMapping
    @SaCheckPermission("donation:save")
    public Result<Void> saveDonation(@RequestBody @Validated DonationSaveDTO dto) {
        Donation donation = BeanUtil.copyProperties(dto, Donation.class);
        donationService.save(donation);
        return Result.ok();
    }

    /**
     * 修改捐赠
     */
    @PutMapping
    @SaCheckPermission("donation:update")
    public Result<Void> updateDonation(@RequestBody @Validated DonationUpdateDTO dto) {
        Donation donation = BeanUtil.copyProperties(dto, Donation.class);
        boolean updated = donationService.updateById(donation);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除捐赠
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("donation:remove")
    public Result<Void> removeDonation(@PathVariable int id) {
        boolean removed = donationService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
