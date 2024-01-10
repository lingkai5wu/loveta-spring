package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.util.SaResult;
import com.github.lingkai5wu.loveta.service.IOssService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 对象存储 前端控制器
 * </p>
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    private final IOssService aliyunOssService;

    public OssController(IOssService aliyunOssService) {
        this.aliyunOssService = aliyunOssService;
    }

    @GetMapping("/post")
    public SaResult getDirectPostObjectParam() {
        return SaResult.data(aliyunOssService.getDirectPostObjectParam());
    }
}
