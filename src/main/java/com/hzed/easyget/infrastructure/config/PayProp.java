package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
*@description：对接放款 收款配置
*@author：[zhangruilin]
*@time：2018/6/14-15:07
**/
@Data
@Configuration
@ConfigurationProperties(prefix = "thirdParty.pay")
public class PayProp {

    private String domain;
    private String loanTransactionUrl;
    private String receiverTransactionUrl;

    public String getAbsLoanTransactionUrl(){ return this.domain + this.loanTransactionUrl; }
    public String getAbsReceiverTransactionUrl(){ return this.domain + this.receiverTransactionUrl; }
}
