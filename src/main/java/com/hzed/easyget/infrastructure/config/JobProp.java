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
    private boolean bankLoan;
    private boolean repayInfoFlow;
    private boolean repayFail;
    private boolean saInData;
    private boolean saLoanSuccess;
    private boolean saRepaymentSuccess;
    private boolean checkBill;
}
