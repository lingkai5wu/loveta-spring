package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.MenuMapper;
import com.github.lingkai5wu.loveta.model.po.Menu;
import com.github.lingkai5wu.loveta.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 服务实现类
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> listMenusByUserId(long id) {
        if (StpUtil.hasRole("super-admin")) {
            return list();
        }
        return baseMapper.listMenusByUserId(id);
    }
}
