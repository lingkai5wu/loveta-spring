package com.github.lingkai5wu.loveta.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.MenuVO;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import com.github.lingkai5wu.loveta.model.vo.aggregate.RuntimeDataVO;
import com.github.lingkai5wu.loveta.service.IMenuService;
import com.github.lingkai5wu.loveta.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聚合
 */
@RestController
@RequestMapping("/aggregate")
@AllArgsConstructor
public class AggregateController {

    private final IUserService userService;
    private final IMenuService menuService;

    /**
     * 获取运行时数据
     */
    @GetMapping("/runtime-data")
    public Result<RuntimeDataVO> getRuntimeDataVO() {
        long id = StpUtil.getLoginIdAsLong();
        RuntimeDataVO dataVO = new RuntimeDataVO()
                .setUserVO(BeanUtil.copyProperties(userService.getById(id), UserVO.class))
                .setMenuVOs(BeanUtil.copyToList(menuService.listMenusByUserId(id), MenuVO.class));
        return Result.data(dataVO);
    }
}
