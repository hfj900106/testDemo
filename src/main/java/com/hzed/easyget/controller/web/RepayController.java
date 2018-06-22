package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ModuleFunc("还款列表")
    @PostMapping("/repaidList")
    public RepayListResponse repaidList() {
        return repayService.repaidList();
    }

    @ModuleFunc("还款详情")
    @PostMapping("/repayDetail")
    public Response<RepayDetailResponse> repayDetail(@Valid @RequestBody RepayDetailRequest request) {
        return Response.getSuccessResponse(repayService.repayDetail(request));
    }

    @ModuleFunc("部分还款详情")
    @PostMapping("/repayPartDetail")
    public Response<RepayPartDetailResponse> repayPartDetail(@Valid @RequestBody RepayPartDetailRequest request) {
        return Response.getSuccessResponse(repayService.repayPartDetail(request));
    }

    @ModuleFunc("部分还款查询")
    @PostMapping("/Fullrepayment")
    public LoanManagResponse fullRepayment(@Valid @RequestBody RepayPartRequest request) {
        LoanManagResponse managResponse = repayService.findloanManagResponse(request.getRepayAmount(), request.getBidId(), false);
        managResponse.setAmount(request.getRepayAmount());
        return managResponse;
    }

    @ModuleFunc("全部还款查询")
    @PostMapping("/Partialrepayment")
    public LoanManagResponse partialRepayment(@Valid @RequestBody RepayAllRequest request) {
        BigDecimal amount = comService.getBidNoRepay(request.getBidId(), LocalDateTime.now());
        LoanManagResponse managResponse = repayService.findloanManagResponse(amount, request.getBidId(), true);
        managResponse.setAmount(amount);
        return managResponse;
    }

    @ModuleFunc("获取VA码")
    @PostMapping("/vaInfoDetail")
    public TransactionVAResponse vaInfoDetail(@Valid @RequestBody TransactionVARequest request) {
        return repayService.findVaTranc(request);
    }

    @ModuleFunc("还款接口(测试环境专用)")
    @PostMapping("/testRepayment")
    public PayResponse testRepayment(@Valid @RequestBody RepaymentRequest request) throws Exception {
        return repayService.testRepayment(request);
    }

    @ModuleFunc("还款接口(正式环境专用)")
    @RequestMapping("/repayment")
    public PayResponse repayment(@Valid @RequestBody RepaymentRequest request) throws Exception {
        return repayService.repayment(request);
    }

    @ModuleFunc("刷新还款结果)")
    @RequestMapping("/refreshResult")
    public PayMentResponse refreshResult(@Valid @RequestBody RefreshPayMentRequest request) throws Exception {
        return repayService.refreshResult(request);
    }
}