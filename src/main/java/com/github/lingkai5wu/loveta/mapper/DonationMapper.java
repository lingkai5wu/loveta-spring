package com.github.lingkai5wu.loveta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lingkai5wu.loveta.model.po.Donation;
import com.github.lingkai5wu.loveta.model.vo.DonationBasicVO;

import java.util.List;

/**
 * 捐赠 Mapper 接口
 *
 * @author lingkai5wu
 * @since 2024-04-10
 */
public interface DonationMapper extends BaseMapper<Donation> {

    List<DonationBasicVO> listDonationBasicVOs();
}
