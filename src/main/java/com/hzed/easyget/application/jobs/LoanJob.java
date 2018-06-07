package com.hzed.easyget.application.jobs;

import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.service.JobService;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Scheduled(cron="0/5 * * * * ?")
    public void pushBid() throws Exception{
        jobService.pushBid();
    }

}
