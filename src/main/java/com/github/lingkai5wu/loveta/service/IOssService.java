package com.github.lingkai5wu.loveta.service;

import com.github.lingkai5wu.loveta.model.vo.OssDirectPostObjectInfoVO;

import java.net.URL;

/**
 * 对象存储 服务类
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
public interface IOssService {

    OssDirectPostObjectInfoVO getOssDirectPostObjectInfoVO();

    URL generateOssGetObjectUrl(String objectName);
}
