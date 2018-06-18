package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
    @RequestMapping("/Fullrepayment")
    public Response<LoanManagResponse> fullRepayment(@Valid @RequestBody RepayPartRequest request){
        LoanManagResponse managResponse=repayService.findloanManagResponse(request.getBidId(),false);
        //部分还款金额
        managResponse.setAmount(request.getRepayAmount());
        return Response.getSuccessResponse(managResponse);
    }
    @ModuleFunc("全部还款查询")
    @RequestMapping("/Partialrepayment")
    public Response<LoanManagResponse> partialRepayment(@Valid @RequestBody RepayAllRequest request){
        LoanManagResponse managResponse=repayService.findloanManagResponse(request.getBidId(),false);
        //获取全部代还总额
        BigDecimal amount=comService.getBidNoRepay(request.getBidId(), LocalDateTime.now());
        managResponse.setAmount(amount);
        return Response.getSuccessResponse(managResponse);
    }

    @ModuleFunc("获取VA码")
    @RequestMapping("/vaInfoDetail")
    public Response<TransactionVAResponse> vaInfoDetail(@Valid @RequestBody TransactionVARequest request){
        TransactionVAResponse vaResponse=repayService.findVATranc(request);
        return Response.getSuccessResponse(vaResponse);
    }
    @ModuleFunc("还款接口")
    @RequestMapping("/repayment")
    public Response repayment(@Valid @RequestBody RepaymentRequest request) throws Exception {
        //先查询交易信息比对数据
        UserTransaction transaction=repayService.selectByKey(request.getPayId());
        if(ObjectUtils.isEmpty(transaction)){
            return Response.getFailResponse();
        }
        if(!transaction.getAmount().equals(request.getAmount())){
            return Response.getFailResponse();
        }
        //凭证图片与后缀数量必须相等
        if(request.getBase64Imgs().length != request.getPicSuffixs().length){
            return Response.getFailResponse();
        }
        PayResponse response= repayService.repayment(request,transaction);
        //走信息流
        if(transaction.getRepaymentType().equals(TransactionTypeEnum.ALL_CLEAR.getCode())){
            repayService.repayAll(new RepayAllRequest(transaction.getBidId()));
        }
        if(transaction.getRepaymentType().equals(TransactionTypeEnum.PARTIAL_CLEARANCE.getCode())){
            repayService.repayPart(new RepayPartRequest(transaction.getBidId(),transaction.getAmount()));
        }
        return Response.getSuccessResponse(response);
    }

}