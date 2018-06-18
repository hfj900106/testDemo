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
    public Response<ProductInfoResponse> getProductInfo() {
        return Response.getSuccessResponse(homeService.getProductInfo());
    }

    @TokenIgnore
    @ModuleFunc("版本号检测是否更新")
    @PostMapping("/getAppVersion")
    public Response<AppVersionResponse> getAppVersion(@Valid @RequestBody AppVersionRequest request) {
        return Response.getSuccessResponse(homeService.getAppVersion(request));

    }

    @TokenIgnore
    @ModuleFunc("产品试算")
    @PostMapping("/loanCalculate")
    public Response<LoanCalculateResponse> loanCalculate(@Valid @RequestBody LoanCalculateRequest request){
        return Response.getSuccessResponse(homeService.loanCalculate(request));
    }


    @ModuleFunc("token更新")
    @PostMapping("/updateToken")
    public Response<UpdateTokenResponse> updateToken(){
        return Response.getSuccessResponse(homeService.updateToken());
    }

    @TokenIgnore
    @ModuleFunc("获取公告列表")
    @PostMapping("/getNewsList")
    public Response<List<NewsResponse>> getNewsList(){
        return Response.getSuccessResponse(homeService.getNewsList());
    }

    @ModuleFunc("发起借款校验")
    @GetMapping("/checkLoan")
    public Response checkLoan(){
        homeService.checkLoan();
        return Response.getSuccessResponse();
    }

    @ModuleFunc("检测首页是否跳转")
    @GetMapping("/checkJump")
    public Response checkJump(){

        homeService.checkJump();
        return Response.getSuccessResponse();
    }

}