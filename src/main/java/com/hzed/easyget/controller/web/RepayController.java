package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.BluePayService;
import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 还款
 *
 * @author wuchengwu
 * @date 2018/6/4
 */
@ExceptionAnno
@RestController
@RequestMapping("/api/repay")
@Slf4j
public class RepayController {

    @Autowired
    private RepayService repayService;
    @Autowired
    private ComService comService;
    @Autowired
    private BluePayService bluePayService;

    @ModuleFunc("还款列表")
    @PostMapping("/repaidList")
    public RepayListResponse repaidList(@Valid @RequestBody RepayListRequest request) {
        return repayService.repaidList(request);
    }

    @ModuleFunc("还款详情")
    @PostMapping("/repayDetail")
    public RepayDetailResponse repayDetail(@Valid @RequestBody RepayDetailRequest request) {
        return repayService.repayDetail(request);
    }

    @ModuleFunc("部分还款详情")
    @PostMapping("/repayPartDetail")
    public RepayPartDetailResponse repayPartDetail(@Valid @RequestBody RepayPartDetailRequest request) {
        return repayService.repayPartDetail(request);
    }

    @ModuleFunc("部分还款")
    @PostMapping("/fullrepayment")
    public PaymentIdResponse fullRepayment(@Valid @RequestBody RepayPartRequest request) {
        return repayService.findloanManagResponse(request.getRepayAmount(), request.getBidId(), false);
    }

    @ModuleFunc("全部还款")
    @PostMapping("/partialrepayment")
    public PaymentIdResponse partialRepayment(@Valid @RequestBody RepayAllRequest request) {
        BigDecimal amount = comService.getBidNoRepayFee(request.getBidId(), LocalDateTime.now());
        return repayService.findloanManagResponse(amount, request.getBidId(), true);
    }

    @ModuleFunc("生成VA码")
    @PostMapping("/vaInfoDetail")
    public TransactionVAResponse vaInfoDetail(@Valid @RequestBody TransactionVARequest request) {
        return bluePayService.findVaTranc(request.getPayId(),request.getMode());
    }

    @ModuleFunc("还款接口(测试环境专用)")
    @PostMapping("/testRepayment")
    public PayResponse testRepayment(@Valid @RequestBody RepaymentCompleRequest request) {
        return bluePayService.testRepayment(request);
    }

    @ModuleFunc("确认转账上传凭证")
    @RequestMapping("/repayment")
    public PayResponse repayment(@Valid @RequestBody RepaymentRequest request) throws Exception {
        return repayService.repayment(request);
    }

    @ModuleFunc("刷新还款结果)")
    @RequestMapping("/refreshResult")
    public PayMentResponse refreshResult(@Valid @RequestBody RefreshPaymentRequest request){
        return repayService.refreshResult(request.getPayId(),request.isExpire());
    }

    @ModuleFunc("查看还款信息)")
    @RequestMapping("/loanManagInfo")
    public LoanManagResponse loanManagInfo(@Valid @RequestBody LoanManagInfoRequest request){
        return repayService.loanManagInfo(request.getPayId());
    }
}