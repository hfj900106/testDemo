package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.HomeService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ModuleFunc("版本号检测是否更新")
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
    @ModuleFunc("获取公告列表")
    @PostMapping("/getNewsList")
    public List<NewsResponse> getNewsList() {
        return homeService.getNewsList();
    }

    @ModuleFunc("发起借款校验")
    @PostMapping("/checkLoan")
    public void checkLoan() {
        homeService.checkLoan();
    }

    @ModuleFunc("检测首页是否跳转")
    @PostMapping("/checkJump")
    public void checkJump() {
        homeService.checkJump();
    }

    @ModuleFunc("检测首页弹框提醒")
    @PostMapping("/homeAlert")
    public Response<HomePageResponse> homeAlert(){
        HomePageResponse response = homeService.homeAlert();
        return Response.getSuccessResponse(response);
    }

}