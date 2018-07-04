package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.BluePayService;
import com.hzed.easyget.application.service.JobService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.BluePayRequest;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.IgnoreHeader;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 放款/还款测试接口
 *
 * @author wuchengwu
 * @date 2018/6/4
 */
@ExceptionAnno
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {
    @Autowired
    private BluePayService bluePayService;
    @Autowired
    private RepayService repayService;
    @Autowired
    private JobService jobService;

    @IgnoreHeader
    @ModuleFunc("还款接口-测试环境专用")
    @PostMapping("/testRepayment")
    public PayResponse testRepayment(@Valid @RequestBody RepaymentCompleRequest request) {
        return bluePayService.testRepayment(request);
    }

    @IgnoreHeader
    @ModuleFunc("人工处理MQ回调")
    @PostMapping("/manMadeMqCallback")
    public void manMadeMqCallback(@Valid @RequestBody BluePayRequest request) {
        log.info("============================= 人工处理MQ回调开始 =============================");
        try {
            repayService.mqCallback(request);
        } catch (Exception ex) {
            throw ex;
        } finally {
            log.info("============================= 人工处理MQ回调结束 =============================");
        }
    }

    @IgnoreHeader
    @ModuleFunc("人工跑定时任务")
    @GetMapping("/manMadeJob/{jobName}")
    public void testRepayment(@PathVariable("jobName") String jobName) {
        try {
            JobService.class.getMethod(jobName).invoke(jobService);
        } catch (NoSuchMethodException e) {
            throw new NestedException(BizCodeEnum.SERVICE_EXCEPTION, "没有此定时任务");
        } catch (Exception e) {
            throw new NestedException(BizCodeEnum.UNKNOWN_EXCEPTION, e.getMessage());
        }


    }
}
