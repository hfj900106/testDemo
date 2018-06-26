package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hfj
 * @date 2018/6/7
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.job")
public class JobProp {
    private boolean pushBid;
    private String pushBidCron;
    private boolean bankLoan;
    private String bankLoanCron;
    private boolean repayInfoFlow;
    private String repayInfoFlowCron;
    private boolean repayFail;
    private String repayFailCron;
    private boolean inData;
    private String inDataCron;
    private boolean loanSuccess;
    private String loanSuccessCron;
    private boolean repaymentSuccess;
    private String repaymentSuccessCron;
}
