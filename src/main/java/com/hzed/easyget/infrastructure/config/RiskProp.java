package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 风控相关配置
 *
 * @author wuchengwu
 * @date 2018/6/14
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thirdParty.risk")
public class RiskProp {
    private String domain;
    private String checkRiskEnableBorrowUrl;

    public String getAbsCheckRiskEnableBorrowUrl(){ return this.domain + this.checkRiskEnableBorrowUrl; }
}