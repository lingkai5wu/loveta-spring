package com.github.lingkai5wu.loveta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lingkai5wu.loveta.model.po.Donation;
import com.github.lingkai5wu.loveta.model.vo.DonationBasicVO;

import java.util.List;

/**
 * 捐赠 服务类
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface IDonationService extends IService<Donation> {

    List<DonationBasicVO> listDonationBasicVOs();
}
