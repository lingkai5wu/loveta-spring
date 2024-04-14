package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.PostMapper;
import com.github.lingkai5wu.loveta.model.po.Post;
import com.github.lingkai5wu.loveta.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * 帖子 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-14
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
