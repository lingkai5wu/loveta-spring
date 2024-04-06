package com.github.lingkai5wu.loveta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lingkai5wu.loveta.mapper.ForumMapper;
import com.github.lingkai5wu.loveta.model.po.Forum;
import com.github.lingkai5wu.loveta.service.IForumService;
import org.springframework.stereotype.Service;

/**
 * 板块 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-04-05
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements IForumService {

}
