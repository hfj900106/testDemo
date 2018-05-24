package com.hzed.easyget.infrastructure.config;

import lombok.Data;
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
    private Integer tokenExpire;
}
