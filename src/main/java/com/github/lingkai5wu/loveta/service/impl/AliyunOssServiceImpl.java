package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.json.JSONUtil;
import com.github.lingkai5wu.loveta.config.AliyunOssConfig;
import com.github.lingkai5wu.loveta.service.IOssService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.UTC_MS_FORMAT;

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
    private final AliyunOssConfig aliyunOssConfig;
    private final String host;
    private final DateTime expirationDateTime;

    public AliyunOssServiceImpl(AliyunOssConfig aliyunOssConfig) {
        this.aliyunOssConfig = aliyunOssConfig;
        this.host = "https://" + aliyunOssConfig.getBucket() + "." + aliyunOssConfig.getEndpoint();
        this.expirationDateTime = DateUtil.offsetMinute(DateUtil.date(), aliyunOssConfig.getPolicyValidityMinutes());
    }

    @Override
    public Map<String, Object> getUploadParams() {
        Map<String, Object> params = new HashMap<>(3);
        params.put("host", host);
        params.put("expires", expirationDateTime.getTime());

        // 合法性校验需要的三个参数
        String policyBase64 = getPolicyBase64();
        Map<String, String> authentication = new HashMap<>(3);
        authentication.put("OSSAccessKeyId", aliyunOssConfig.getAccessKeyId());
        authentication.put("policy", policyBase64);
        authentication.put("signature", getSignature(policyBase64));
        params.put("authentication", authentication);

        return params;
    }

    private String getPolicyBase64() {
        Map<String, Object> policy = new HashMap<>();
        policy.put("expiration", DateUtil.format(expirationDateTime, UTC_MS_FORMAT));
        policy.put("conditions", new Object[][]{
                {"content-length-range", 0, aliyunOssConfig.getMaxContentLengthMB() * 1000 * 1000},
                // 一定要注意 Conditions 的值类型，除了 content-length-range，其他应该都是字符串
                // https://help.aliyun.com/zh/oss/developer-reference/postobject
                {"eq", "$x-oss-meta-user-id", StpUtil.getLoginIdAsString()}
        });
        String jsonStr = JSONUtil.toJsonStr(policy);
        return Base64.encode(jsonStr);
    }

    private String getSignature(String policyBase64) {
        HMac mac = new HMac(HmacAlgorithm.HmacSHA1, aliyunOssConfig.getAccessKeySecret().getBytes());
        return mac.digestBase64(policyBase64, false);
    }
}
