package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.AnimalCategoryMapper;
import com.github.lingkai5wu.loveta.model.po.AnimalCategory;
import com.github.lingkai5wu.loveta.service.IAnimalCategoryService;
import org.springframework.stereotype.Service;

/**
 * 动物分类 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-04
 */
@Service
public class AnimalCategoryServiceImpl extends ServiceImpl<AnimalCategoryMapper, AnimalCategory> implements IAnimalCategoryService {

}