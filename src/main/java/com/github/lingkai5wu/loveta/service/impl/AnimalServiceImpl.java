package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.AnimalMapper;
import com.github.lingkai5wu.loveta.model.po.Animal;
import com.github.lingkai5wu.loveta.service.IAnimalService;
import org.springframework.stereotype.Service;

/**
 * 动物 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-02
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {

}
