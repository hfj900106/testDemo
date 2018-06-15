package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ModuleFunc("还款列表")
    @GetMapping("/repaidList")
    public Response<RepayListResponse> repaidList(){
        return Response.getSuccessResponse(repayService.repaidList());
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
    public Response<LoanManagResponse> repayDetail(@Valid @RequestBody RepayDetailRequest request){
        try {
            LoanManagResponse loanManagResponse=repayService.findloanManagResponse(request.getBidId(),true);
            return Response.getSuccessResponse(loanManagResponse);
        }catch (Exception e) {
            e.getStackTrace();
            log.error("结清全部处理异常{}",e.getMessage());
            return Response.getFailResponse();
        }
    }

    @ModuleFunc("部分还款详情")
    @RequestMapping("/repayPartDetail")
    public Response<LoanManagResponse> repayPartDetail(@Valid @RequestBody RepayPartRequest request){
        try {
            LoanManagResponse loanManagResponse=repayService.findloanManagResponse(request.getBidId(),false);
            loanManagResponse.setAmount(request.getRepayAmount());
            return Response.getSuccessResponse(loanManagResponse);
        }catch (Exception e) {
            e.getStackTrace();
            log.error("结清全部处理异常{}",e.getMessage());
            return Response.getFailResponse();
        }
    }
    @ModuleFunc("获取VA码")
    @RequestMapping("/vaInfoDetail")
    public Response<LoanManagResponse> vaInfoDetail(@Valid @RequestBody RepayPartRequest request){
        try {
            return  null;
        }catch (Exception e) {
            e.getStackTrace();
            log.error("结清全部处理异常{}",e.getMessage());
            return Response.getFailResponse();
        }
    }


}