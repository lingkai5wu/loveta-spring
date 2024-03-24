package com.github.lingkai5wu.loveta.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.lingkai5wu.loveta.model.Result;
import com.github.lingkai5wu.loveta.model.vo.OssDirectPostObjectInfoVO;
import com.github.lingkai5wu.loveta.service.IOssService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对象存储
 */
@RestController
@RequestMapping("/oss")
@AllArgsConstructor
public class OssController {

    private final IOssService aliyunOssService;

    /**
     * 获取表单直传参数
     */
    @GetMapping("/direct-post-object-info")
    @SaCheckPermission("oss:post")
    public Result<OssDirectPostObjectInfoVO> getDirectPostObjectInfo() {
        return Result.data(aliyunOssService.getDirectPostObjectInfo());
    }
}
