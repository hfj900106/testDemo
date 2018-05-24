package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.HomeService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页相关
 *
 * @author wuchengwu
 * @data 2018/5/22
 */

@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ModuleFunc("获取产品详情")
    @PostMapping("/getProductInfo")
    public Response<ProductInfoResponse> getProductInfo() {
        return Response.getSuccessResponse(homeService.getProductInfo());
    }

    @ModuleFunc("版本号检测是否更新")
    @PostMapping("/getAppVersion")
    public Response<AppVersionResponse> getAppVersion(@RequestBody AppVersionRequest request) {
        return Response.getSuccessResponse(homeService.getAppVersion(request));

    }

    @ModuleFunc("产品试算")
    @PostMapping("/loanCalculate")
    public Response<LoanCalculateResponse> loanCalculate(@RequestBody LoanCalculateRequest request){
        return Response.getSuccessResponse(homeService.loanCalculate(request));
    }

    @ModuleFunc("token更新")
    @PostMapping("/updateToken")
    public Response<UpdateTokenResponse> updateToken(){

// 刷新token接口 token未过期的情况
        // 1、从当前token拿到 GlobalUser 调用
//        String newToken = JwtUtil.createToken(oldGlobalUser);
        // 更新到t_user_token表 redis 3个小时
        // 将新token返回给APP
        return null;
    }
}