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

    @JobAnnotation("神策进件")
    @Scheduled(cron = "${system.job.saInDataCron}")
    public void saInData() {
        jobService.saInData();
    }

    @JobAnnotation("神策借款成功")
    @Scheduled(cron = "${system.job.saLoanSuccessCron}")
    public void saLoanSuccess() {
        jobService.saLoanSuccess();
    }

    @JobAnnotation("神策还款成功")
    @Scheduled(cron = "${system.job.saRepaymentSuccessCron}")
    public void saRepaymentSuccess() {
        jobService.saRepaymentSuccess();
    }

    @JobAnnotation("短信催账D2")
    @Scheduled(cron = "${system.job.checkBillCron_D2}")
    public void checkBill_D2() {
        jobService.checkBillD1AndD2(2);
    }

    @JobAnnotation("短信催账D1")
    @Scheduled(cron = "${system.job.checkBillCron_D1}")
    public void checkBill_D1() {
        jobService.checkBillD1AndD2(1);
    }

    @JobAnnotation("短信催账D0")
    @Scheduled(cron = "${system.job.checkBillCron_D0}")
    public void checkBill_D0() {
        jobService.checkBillD0(0);
    }
}
