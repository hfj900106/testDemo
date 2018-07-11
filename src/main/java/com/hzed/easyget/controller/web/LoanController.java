package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.LoanService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 借款相关
 *
 * @author wuchengwu
 * @date 2018/6/7
 */
@ExceptionAnno
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @ModuleFunc("借款详情")
    @PostMapping("/loanDetail")
    public LoanDetailResponse loanDetail(@Valid @RequestBody LoanDetailRequest request) {
        return loanService.loanDetail(request);
    }


    @ModuleFunc("预申请贷款")
    @PostMapping("/preSubmitLoan")
    public PreSubmitLoanResponse preSubmitLoan(@Valid @RequestBody PreSubmitLoanRequest request){
        return loanService.preSubmitLoan(request);
    }
    @ModuleFunc("申请借款")
    @PostMapping("/submitLoan")
    public SubmitLoanResponse submitLoan(@Valid @RequestBody SubmitLoanRequest request) {
        return loanService.submitLoan(request);
    }
}