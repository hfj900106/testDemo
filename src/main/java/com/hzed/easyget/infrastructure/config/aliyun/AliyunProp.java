package com.hzed.easyget.infrastructure.config.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云配置信息
 * @author guichang
 * @date 2018/6/5
 */
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunProp {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
