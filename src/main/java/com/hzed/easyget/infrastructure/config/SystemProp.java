package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

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
    private String[] TelkomselMobilePrefix;
    private String[] XLMobilePrefix;
    private String[] IndosatMobilePrefix;
    private MobilePrefix mobilePrefix;
    private BigDecimal authFee;
    private BigDecimal reviewFee;
    private BigDecimal handlingFee;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "system.mobilePrefix")
    public static class MobilePrefix {
        private String Telkomsel;
        private String XL;
        private String Indosat;
    }

}
