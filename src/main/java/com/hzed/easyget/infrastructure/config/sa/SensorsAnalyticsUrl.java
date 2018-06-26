package com.hzed.easyget.infrastructure.config.sa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 神策url
 * @author wuchengwu
 * @since 2018/6/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sensorsAnalyticsUrl")
public class SensorsAnalyticsUrl {

    private String saServerLogUrl;

    public String getSaServerLogUrl() {
        return saServerLogUrl;
    }
}
