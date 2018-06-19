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
    @GetMapping("/repaidList")
    public RepayListResponse repaidList() {
        return repayService.repaidList();
    }

    @ModuleFunc("结清全部")
    @PostMapping("/repayAll")
    public Response repayAll(@Valid @RequestBody RepayAllRequest request){
        repayService.repayAll(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("部分还款")
    @PostMapping("/repayPart")
    public Response repayPart(@Valid @RequestBody RepayPartRequest request){
        repayService.repayPart(request);

        return Response.getSuccessResponse();
    }

    @ModuleFunc("还款详情")
    @GetMapping("/repayDetail")
    public Response<RepayDetailResponse> repayDetail(@Valid @RequestBody RepayDetailRequest request){
        return Response.getSuccessResponse(repayService.repayDetail(request));
    }

    @ModuleFunc("部分还款详情")
    @RequestMapping("/repayPartDetail")
    public Response<RepayPartDetailResponse> repayPartDetail(@Valid @RequestBody RepayPartDetailRequest request){
        return Response.getSuccessResponse(repayService.repayPartDetail(request));
    }

    /**
     * ---------------------------------------------------------------------
     * @param request
     * @return
     */
    @ModuleFunc("部分还款查询")
    @PostMapping("/Fullrepayment")
    public LoanManagResponse fullRepayment(@Valid @RequestBody RepayPartRequest request){
        LoanManagResponse managResponse=repayService.findloanManagResponse(request.getRepayAmount(),request.getBidId(),false);
        //部分还款金额
        managResponse.setAmount(request.getRepayAmount());
        return managResponse;
    }
    @ModuleFunc("全部还款查询")
    @PostMapping("/Partialrepayment")
    public LoanManagResponse partialRepayment(@Valid @RequestBody RepayAllRequest request){
        //获取全部代还总额
        BigDecimal amount=comService.getBidNoRepay(request.getBidId(), LocalDateTime.now());
        LoanManagResponse managResponse=repayService.findloanManagResponse(amount,request.getBidId(),false);
        managResponse.setAmount(amount);
        return managResponse;
    }

    @ModuleFunc("获取VA码")
    @RequestMapping("/vaInfoDetail")
    public TransactionVAResponse vaInfoDetail(@Valid @RequestBody TransactionVARequest request){
        TransactionVAResponse vaResponse=repayService.findVATranc(request);
        return vaResponse;
    }
    @ModuleFunc("还款接口(测试环境专用)")
    @RequestMapping("/repayment")
    public Response repayment(@Valid @RequestBody RepaymentRequest request) throws Exception {
        PayResponse response= repayService.repayment(request);
        return Response.getSuccessResponse(response);
    }

}