package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.BluePayService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ModuleFunc("还款接口(测试环境专用)")
    @PostMapping("/testRepayment")
    public PayResponse testRepayment(@Valid @RequestBody RepaymentCompleRequest request) {
        return bluePayService.testRepayment(request);
    }

    @ModuleFunc("放款/还款回调接口(测试环境专用)")
    @PostMapping("/mqCallBackConsumer")
    public void mqCallBackConsumer(String request) {
        MdcUtil.putModuleName("人工处理MQ回调");
        // 记录trace，方便日志追踪
        MdcUtil.putTrace();
        log.info("============================= 人工处理MQ回调开始 =============================");
        log.info("原始请求报文：{}",request);
        repayService.mqCallBackConsumer(request);
        log.info("============================= 人工处理MQ回调结束 =============================");
    }
}
