package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.HomeService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.TokenIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 首页相关
 *
 * @author wuchengwu
 * @data 2018/5/22
 */

@ExceptionAnno
@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @TokenIgnore
    @ModuleFunc("获取产品详情")
    @PostMapping("/getProductInfo")
    public ProductInfoResponse getProductInfo() {
        return homeService.getProductInfo();
    }

    @TokenIgnore
    @ModuleFunc("版本检测")
    @PostMapping("/getAppVersion")
    public AppVersionResponse getAppVersion(@Valid @RequestBody AppVersionRequest request) {
        return homeService.getAppVersion(request);
    }

    @TokenIgnore
    @ModuleFunc("产品试算")
    @PostMapping("/loanCalculate")
    public LoanCalculateResponse loanCalculate(@Valid @RequestBody LoanCalculateRequest request) {
        return homeService.loanCalculate(request);
    }

    @ModuleFunc("token更新")
    @PostMapping("/updateToken")
    public UpdateTokenResponse updateToken() {
        return homeService.updateToken();
    }

    @TokenIgnore
    @ModuleFunc("获取公告")
    @PostMapping("/getMessage")
    public MessageResponse getMessage() {
        return homeService.getMessage();
    }

    @ModuleFunc("发起借款校验")
    @PostMapping("/checkLoan")
    public List<CheckLoanResponse> checkLoan() {
        return homeService.checkLoan();
    }

    @ModuleFunc("借款状态检测")
    @PostMapping("/checkLoanJump")
    public CheckLoanJumpResponse checkLoanJump() {
        return homeService.checkLoanJump();
    }

    @ModuleFunc("还款状态检测")
    @PostMapping("/checkRepayment")
    public CheckRepaymentResponse checkRepayment() {
        return homeService.checkRepayment();
    }




}