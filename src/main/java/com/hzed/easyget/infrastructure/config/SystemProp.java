package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统参数配置
 * @author guichang
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemProp {
    @Value("${spring.profiles.active}")
    private String env;
    private Integer tokenExpire;
    private Long expectedAuditTimeInterval;
    private Long expectedLendingTimeInterval;
    private String local;
    private Integer daysInAdvance;
    private Integer expiredDay;
    private String H5MessageUrl;

}
