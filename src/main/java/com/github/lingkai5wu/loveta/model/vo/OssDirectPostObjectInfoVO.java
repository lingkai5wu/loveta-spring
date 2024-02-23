package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OssDirectPostObjectInfoVO {
    /**
     * 请求域名
     */
    @NotNull
    private String host;

    /**
     * policy 过期时间
     */
    @NotNull
    private long expire;

    /**
     * AccessKey ID
     */
    @NotNull
    private String ossAccessKeyId;

    /**
     * 上传策略
     */
    @NotNull
    private String policy;

    /**
     * policy 签名
     */
    @NotNull
    private String signature;
}
