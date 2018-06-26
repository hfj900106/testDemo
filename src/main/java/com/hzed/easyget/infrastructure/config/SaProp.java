package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 神策配置
 * @author wuchengwu
 * @since 2018/6/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system")
public class SaProp {

    private String saServerLogUrl;
}
