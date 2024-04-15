package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lingkai5wu.loveta.model.PageDTO;
import com.github.lingkai5wu.loveta.model.PageVO;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.PostSaveDTO;
import com.github.lingkai5wu.loveta.model.dto.PostUpdateDTO;
import com.github.lingkai5wu.loveta.model.po.Post;
import com.github.lingkai5wu.loveta.model.query.PostQuery;
import com.github.lingkai5wu.loveta.model.vo.PostBasicVO;
import com.github.lingkai5wu.loveta.model.vo.PostVO;
import com.github.lingkai5wu.loveta.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final IPostService postService;

    /**
     * 获取帖子
     */
    @GetMapping("/{id}")
    @SaCheckPermission("post:get")
    public Result<PostVO> getPostVO(@PathVariable int id) {
        Post post = postService.getById(id);
        if (post == null) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        PostVO postVO = BeanUtil.copyProperties(post, PostVO.class);
        return Result.data(postVO);
    }

    /**
     * 列出当前用户帖子
     */
    @GetMapping("/current")
    public Result<List<PostBasicVO>> listCurrentPostBasicVOs() {
        int id = StpUtil.getLoginIdAsInt();
        List<Post> postList = postService.lambdaQuery()
                .eq(Post::getUserId, id)
                .list();
        List<PostBasicVO> postBasicVOList = BeanUtil.copyToList(postList, PostBasicVO.class);
        return Result.data(postBasicVOList);
    }

    /**
     * 列出全部帖子基本信息
     */
    @GetMapping
    @SaCheckPermission("post:list")
    public Result<List<PostBasicVO>> listPostBasicVOs() {
        List<PostBasicVO> postBasicVOList = BeanUtil.copyToList(postService.list(), PostBasicVO.class);
        return Result.data(postBasicVOList);
    }

    /**
     * 分页列出全部帖子基本信息
     */
    @GetMapping("/page")
    @SaCheckPermission("post:page")
    public Result<PageVO<PostBasicVO>> listPostBasicVOsWithPage(PostQuery query, PageDTO pageDTO) {
        Page<Post> page = new Page<>();
        BeanUtil.copyProperties(pageDTO, page, new CopyOptions().ignoreNullValue());
        QueryWrapper<Post> wrapper = new QueryWrapper<>(BeanUtil.copyProperties(query, Post.class));
        page = postService.page(page, wrapper);
        List<PostBasicVO> postBasicVOList = BeanUtil.copyToList(page.getRecords(), PostBasicVO.class);
        return Result.page(postBasicVOList, page);
    }

    /**
     * 新增帖子
     */
    @PostMapping
    @SaCheckPermission("post:save")
    public Result<Void> savePost(@RequestBody @Validated PostSaveDTO dto) {
        Post post = BeanUtil.copyProperties(dto, Post.class);
        post.setUserId(StpUtil.getLoginIdAsInt());
        postService.save(post);
        return Result.ok();
    }

    /**
     * 修改帖子
     */
    @PutMapping
    @SaCheckPermission("post:update")
    public Result<Void> updatePost(@RequestBody @Validated PostUpdateDTO dto) {
        Post post = BeanUtil.copyProperties(dto, Post.class);
        boolean updated = postService.updateById(post);
        if (!updated) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }

    /**
     * 删除帖子
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("post:remove")
    public Result<Void> removePost(@PathVariable int id) {
        boolean removed = postService.removeById(id);
        if (!removed) {
            return Result.status(HttpStatus.NOT_FOUND);
        }
        return Result.ok();
    }
}
