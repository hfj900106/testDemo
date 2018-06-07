package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hfj
 * @date 2018/6/7
 */
@Data
@ConfigurationProperties(prefix="system.loanJob")
public class JobProp {
    private Boolean pushBid;
    private String pushBidCron;
}
