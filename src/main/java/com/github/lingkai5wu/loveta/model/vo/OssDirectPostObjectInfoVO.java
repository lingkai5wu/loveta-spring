package com.github.lingkai5wu.loveta.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对象存储表单直传参数 显示层对象
 *
 * @author lingkai5wu
 * @since 2024-02-01
 */
@Data
@Accessors(chain = true)
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
     * Object 最大大小
     */
    @NotNull
    private long maxSize;

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
