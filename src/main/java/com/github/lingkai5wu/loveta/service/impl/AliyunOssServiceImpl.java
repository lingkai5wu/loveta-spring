package com.github.lingkai5wu.loveta.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PolicyConditions;
import com.github.lingkai5wu.loveta.config.AliyunOssConfig;
import com.github.lingkai5wu.loveta.enums.OssImageProcessStyleEnum;
import com.github.lingkai5wu.loveta.model.dto.OssGetObjectUrlGenerateDTO;
import com.github.lingkai5wu.loveta.model.vo.OssDirectPostObjectInfoVO;
import com.github.lingkai5wu.loveta.service.IOssService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 阿里云 对象存储 服务实现类
 *
 * @author lingkai5wu
 * @since 2024-01-08
 */
@Service
@AllArgsConstructor
public class AliyunOssServiceImpl implements IOssService {

    private final OSS client;
    private final AliyunOssConfig config;

    @Override
    public URL generateOssGetObjectUrl(OssGetObjectUrlGenerateDTO dto) {
        long expire = (long) (System.currentTimeMillis() + config.getGetObjectUrlValidSeconds() * 1000);
        Date expiration = new Date(expire);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(config.getBucket(), dto.getObjectName(),
                HttpMethod.GET);
        request.setExpiration(expiration);
        if (dto.getImageProcessStyle() == null) {
            request.setProcess("style/" + OssImageProcessStyleEnum.NORMAL.getName());
        } else {
            request.setProcess("style/" + dto.getImageProcessStyle().getName());
        }
        return client.generatePresignedUrl(request);
    }

    @Override
    public OssDirectPostObjectInfoVO getOssDirectPostObjectInfoVO() {
        String host = String.format("https://%s.%s", config.getBucket(), config.getEndpoint());
        long expire = (long) (System.currentTimeMillis() + config.getPostObjectPolicyValidSeconds() * 1000);
        long maxSizeAllowed = (long) (config.getPostObjectMaxSizeAllowedMebibyte() * 1024 * 1024);

        PolicyConditions conditions = new PolicyConditions();
        conditions.addConditionItem(
                PolicyConditions.COND_CONTENT_LENGTH_RANGE,
                0,
                maxSizeAllowed);
        conditions.addConditionItem(
                PolicyConditions.COND_X_OSS_META_PREFIX + "userId",
                StpUtil.getLoginIdAsString()
        );
        String rowPolicy = client.generatePostPolicy(new Date(expire), conditions);
        byte[] policyBytes = rowPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(policyBytes);

        String signature = client.calculatePostSignature(rowPolicy);

        return new OssDirectPostObjectInfoVO()
                .setHost(host)
                .setExpire(expire)
                .setMaxSize(maxSizeAllowed)
                .setOssAccessKeyId(config.getAccessKeyId())
                .setPolicy(encodedPolicy)
                .setSignature(signature);
    }
}
