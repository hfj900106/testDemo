package com.hzed.easyget.application.jobs;

import com.hzed.easyget.application.service.JobService;
import com.hzed.easyget.infrastructure.annotation.JobAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * <p>
 * 注意：
 * 1、定时任务的方法名必须与application.yml中的system.job下的属性一致才能生效
 * 2、有异常直接抛出
 *
 * @author guichang
 * @date 2018/06/11
 */

@Slf4j
@Component
public class LoanJob {
    @Autowired
    private JobService jobService;

    @JobAnnotation("走风控")
    @Scheduled(cron = "0/5 * * * * ?")
    public void pushBid() throws Exception {
        jobService.pushBid();
    }

    @JobAnnotation("银行放款")
    @Scheduled(cron = "0/5 * * * * ?")
    public void bankLoan() throws Exception {
        jobService.bankLoan();
    }



}
