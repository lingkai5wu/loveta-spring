package com.github.lingkai5wu.loveta.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云对象存储 配置类
 *
 * @author lingkai5wu
 * @since 2024-01-09
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint = "oss.aliyuncs.com";
    private String bucket;
    private Double getObjectUrlValidSeconds = 60.0;
    private Double postObjectPolicyValidSeconds = 60.0;
    private Double postObjectMaxSizeAllowedMebibyte = 10.0;

    @Bean
    public OSS aliyunOSSClient() {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        return new OSSClientBuilder().build(endpoint, credentialsProvider);
    }
}
