package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.LoanService;
import com.hzed.easyget.controller.model.LoanDetailRequest;
import com.hzed.easyget.controller.model.LoanDetailResponse;
import com.hzed.easyget.controller.model.SubmitLoanRequest;
import com.hzed.easyget.controller.model.SubmitLoanResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/loanDetail")
    public LoanDetailResponse loanDetail(@Valid @RequestBody LoanDetailRequest request) {

        return loanService.loanDetail(request);
    }

    @ModuleFunc("申请借款")
    @RequestMapping("/submitLoan")
    public SubmitLoanResponse submitLoan(@Valid @RequestBody SubmitLoanRequest request) {
        return loanService.submitLoan(request);
    }
}