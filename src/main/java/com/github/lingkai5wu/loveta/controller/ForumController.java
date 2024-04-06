package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.ForumSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.ForumUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Forum;
import com.github.lingkai5wu.loveta.model.vo.ForumBasicVO;
import com.github.lingkai5wu.loveta.model.vo.ForumVO;
import com.github.lingkai5wu.loveta.service.IForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 板块
 *
 * @author lingkai5wu
 * @since 2024-04-05
 */
@RestController
@RequestMapping("/forums")
@AllArgsConstructor
public class ForumController {

    private final IForumService forumService;

    /**
     * 获取板块
     */
    @GetMapping("/{id}")
    @SaCheckPermission("forum:get")
    public Result<ForumVO> getForumVO(@PathVariable int id) {
        Forum forum = forumService.getById(id);
        if (forum == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        ForumVO forumVO = BeanUtil.copyProperties(forum, ForumVO.class);
        return Result.data(forumVO);
    }

    /**
     * 列出全部板块
     */
    @GetMapping
    @SaCheckPermission("forum:list")
    public Result<List<ForumBasicVO>> listForumVOs() {
        List<ForumBasicVO> forumBasicVOList = BeanUtil.copyToList(forumService.list(), ForumBasicVO.class);
        return Result.data(forumBasicVOList);
    }

    /**
     * 新增板块
     */
    @PostMapping
    @SaCheckPermission("forum:save")
    public Result<Void> saveForum(@RequestBody @Validated ForumSaveDTO dto) {
        Forum forum = BeanUtil.copyProperties(dto, Forum.class);
        forumService.save(forum);
        return Result.ok();
    }

    /**
     * 修改板块
     */
    @PutMapping
    @SaCheckPermission("forum:update")
    public Result<Void> updateForum(@RequestBody @Validated ForumUpdateDTO dto) {
        Forum forum = BeanUtil.copyProperties(dto, Forum.class);
        boolean updated = forumService.updateById(forum);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除板块
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("forum:remove")
    public Result<Void> removeForum(@PathVariable int id) {
        boolean removed = forumService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
