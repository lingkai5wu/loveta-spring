package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.MenuVO;
import com.github.lingkai5wu.loveta.model.vo.aggregate.RuntimeVO;
import com.github.lingkai5wu.loveta.service.IMenuService;
import com.github.lingkai5wu.loveta.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聚合
 */
@RestController
@RequestMapping("/aggregate")
public class AggregateController {
    private final IUserService userService;
    private final IMenuService menuService;

    public AggregateController(IUserService userService, IMenuService menuService) {
        this.userService = userService;
        this.menuService = menuService;
    }

    /**
     * 获取运行时数据
     */
    @GetMapping("/runtime")
    public Result<RuntimeVO> getRuntimeVO() {
        long id = StpUtil.getLoginIdAsLong();
        RuntimeVO runtimeVO = new RuntimeVO()
                .setUserVO(userService.getUserVOById(id))
                .setMenuVOList(BeanUtil.copyToList(menuService.listMenusByUserId(id), MenuVO.class));
        return Result.data(runtimeVO);
    }
}
