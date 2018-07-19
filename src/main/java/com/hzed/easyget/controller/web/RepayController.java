package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    public TransactionVaResponse vaInfoDetail(@Valid @RequestBody TransactionVaRequest request) {
        return repayService.findVaTranc(request);
    }


    @ModuleFunc("已生成va码列表")
    @PostMapping("/getVaHistory")
    public List<VaHistoryResponse> getVaHistory(@Valid @RequestBody VaHistoryRequest request) {
        return repayService.getVaHistory(request);
    }

    @ModuleFunc(value = "提交图片凭证", printParameterLength = 300)
    @PostMapping("/uploadPicEvidence")
    public void uploadPicEvidence(@Valid @RequestBody UploadPicEvidenceRequest request) {
        repayService.uploadPicEvidence(request);
    }

    @ModuleFunc("获取还款进度")
    @PostMapping("/getRepayProgress")
    public RepayProgressResponse getRepayProgress(@Valid @RequestBody RepayDetailRequest request){
        return repayService.getRepayProgress(request);
    }
}