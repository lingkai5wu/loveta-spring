package com.github.lingkai5wu.loveta.service;

import com.github.lingkai5wu.loveta.model.vo.OssDirectPostObjectInfoVO;

import java.net.URL;

/**
 * 对象存储 前端控制器
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
public interface IOssService {
    OssDirectPostObjectInfoVO getDirectPostObjectInfo();

    URL getGetObjectUrl(String objectName);
}
