package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方
 *
 * @author wuchengwu
 * @since 2018/6/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thirdParty")
public class ThirdPartyProp {
    private String domain;
    private String loanTransactionUrl;
    private String receiverTransactionUrl;

    public String getAbsLoanTransactionUrl(){ return this.domain + this.loanTransactionUrl; }
    public String getAbsReceiverTransactionUrl(){ return this.domain + this.receiverTransactionUrl; }
}