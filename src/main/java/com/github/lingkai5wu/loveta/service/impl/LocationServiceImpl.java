package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.LocationMapper;
import com.github.lingkai5wu.loveta.model.po.Location;
import com.github.lingkai5wu.loveta.service.ILocationService;
import org.springframework.stereotype.Service;

/**
 * 位置 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-03
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {

}