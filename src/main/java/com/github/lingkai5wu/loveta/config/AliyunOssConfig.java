package com.github.lingkai5wu.loveta.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    private String bucket = "loveta";
    private Integer policyValidityMinutes = 60;
    private Integer maxContentLengthMB = 10;
}
