package com.hzed.easyget.application.jobs;

import com.hzed.easyget.application.service.JobService;
import com.hzed.easyget.infrastructure.annotation.JobAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author hfj
 * @date 2018/6/7
 */
@Slf4j
@Component
public class LoanJob {
    @Autowired
    private JobService jobService;

    @JobAnnotation("走风控")
    @Scheduled(cron="0/5 * * * * ?")
    public void pushBid() throws Exception{
        jobService.pushBid();
    }

}
