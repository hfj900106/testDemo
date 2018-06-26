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

    @JobAnnotation("风控审核")
    @Scheduled(cron = "${system.job.pushBidCron}")
    public void pushBid() {
        jobService.pushBid();
    }

    @JobAnnotation("放款")
    @Scheduled(cron = "${system.job.bankLoanCron}")
    public void bankLoan() {
        jobService.bankLoan();
    }

    @JobAnnotation("处理还款信息流")
    @Scheduled(cron = "${system.job.repayInfoFlowCron}")
    public void repayInfoFlow() {
        jobService.repayInfoFlow();
    }

    @JobAnnotation("处理还款失败结果")
    @Scheduled(cron = "${system.job.repayFailCron}")
    public void repayFail() {
        jobService.repayFail();
    }

    @JobAnnotation("神策进件")
    @Scheduled(cron = "0/5 * * * * ?")
    public void inData() throws Exception {
        jobService.inData();
    }

    @JobAnnotation("神策借款成功")
    @Scheduled(cron = "0/5 * * * * ?")
    public void loanSuccess() throws Exception {
        jobService.loanSuccess();
    }

    @JobAnnotation("神策还款成功")
    @Scheduled(cron = "0/5 * * * * ?")
    public void repaymentSuccess() throws Exception {
        jobService.repaymentSuccess();
    }
}
