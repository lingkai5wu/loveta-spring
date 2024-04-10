package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.dto.OssGetObjectUrlGenerateDTO;
import com.github.lingkai5wu.loveta.model.vo.OssDirectPostObjectInfoVO;
import com.github.lingkai5wu.loveta.service.IOssService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

/**
 * 对象存储
 *
 * @author lingkai5wu
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/oss")
@AllArgsConstructor
public class OssController {

    private final IOssService aliyunOssService;

    /**
     * 生成获取文件 URL
     */
    @GetMapping("/get-object-url")
    @SaCheckPermission("oss:get")
    public Result<URL> generateOssGetObjectUrl(@Validated OssGetObjectUrlGenerateDTO dto) {
        return Result.data(aliyunOssService.generateOssGetObjectUrl(dto));
    }

    /**
     * 获取表单直传参数
     */
    @GetMapping("/direct-post-object-info")
    @SaCheckPermission("oss:post")
    public Result<OssDirectPostObjectInfoVO> getOssDirectPostObjectInfoVO() {
        return Result.data(aliyunOssService.getOssDirectPostObjectInfoVO());
    }
}
