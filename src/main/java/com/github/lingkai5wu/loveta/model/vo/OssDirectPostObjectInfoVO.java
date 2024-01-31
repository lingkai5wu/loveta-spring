package com.github.lingkai5wu.loveta.model.vo;

import lombok.Data;

@Data
public class OssDirectPostObjectInfoVO {
    /**
     * Bucket 域名
     */
    private String host;

    /**
     * 过期时间
     */
    private long expire;

    /**
     * AccessKey ID
     */
    private String ossAccessKeyId;

    /**
     * 请求合法性验证策略
     */
    private String policy;

    /**
     * 签名
     */
    private String signature;
}
