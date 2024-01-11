package com.github.lingkai5wu.loveta.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PolicyConditions;
import com.github.lingkai5wu.loveta.config.AliyunOssConfig;
import com.github.lingkai5wu.loveta.service.IOssService;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 阿里云对象存储 前端控制器
 * </p>
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
@Service
public class AliyunOssServiceImpl implements IOssService {
    private final OSS client;
    private final AliyunOssConfig config;

    public AliyunOssServiceImpl(OSS client, AliyunOssConfig config) {
        this.client = client;
        this.config = config;
    }

    @Override
    public Map<String, Object> getDirectPostObjectParam() {
        Map<String, Object> param = new HashMap<>();

        String host = String.format("https://%s.%s", config.getBucket(), config.getEndpoint());
        param.put("host", host);

        long expire = (long) (System.currentTimeMillis() + config.getPostObjectPolicyValiditySecond() * 1000);
        param.put("expire", expire);

        Map<String, Object> auth = new HashMap<>();
        auth.put("ossAccessKeyId", config.getAccessKeyId());

        PolicyConditions conditions = new PolicyConditions();
        conditions.addConditionItem(
                PolicyConditions.COND_CONTENT_LENGTH_RANGE,
                0,
                (long) (config.getPostObjectMaxContentLengthMebibyte() * 1024 * 1024));
        Date expiration = new Date(expire);
        String policy = client.generatePostPolicy(expiration, conditions);
        byte[] policyBytes = policy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(policyBytes);
        auth.put("policy", encodedPolicy);

        String signature = client.calculatePostSignature(policy);
        auth.put("signature", signature);

        param.put("auth", auth);

        return param;
    }

    @Override
    public URL getGetObjectUrl(String objectName) {
        long expire = (long) (System.currentTimeMillis() + config.getPostObjectPolicyValiditySecond() * 1000);
        Date expiration = new Date(expire);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(config.getBucket(), objectName, HttpMethod.GET);
        request.setExpiration(expiration);
        return client.generatePresignedUrl(request);
    }
}
