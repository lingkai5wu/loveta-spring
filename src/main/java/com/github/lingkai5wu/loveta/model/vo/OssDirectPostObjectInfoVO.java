package com.github.lingkai5wu.loveta.model.vo;

import lombok.Data;

@Data
public class OssDirectPostObjectInfoVO {
    /**
     * 请求域名
     */
    private String host;

    /**
     * policy 过期时间
     */
    private long expire;

    /**
     * AccessKey ID
     */
    private String ossAccessKeyId;

    /**
     * 上传策略
     */
    private String policy;

    /**
     * policy 签名
     */
    private String signature;
}
