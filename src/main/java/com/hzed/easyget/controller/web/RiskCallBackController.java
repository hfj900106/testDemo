package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.RiskCallBackService;
import com.hzed.easyget.controller.model.PushBidCallBackRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 风控回调
 * @author hfj
 * @date 2018/6/9
 */
@ExceptionAnno
@RestController
@RequestMapping("/easy-get/riskCallBack")
public class RiskCallBackController {
    @Autowired
    private RiskCallBackService riskCallBackService;

    @ModuleFunc("推送资产-审核回调")
    @PostMapping("/pushBidCallBack")
    public Response pushBidCallBack(@RequestBody PushBidCallBackRequest request) {
        riskCallBackService.pushBidCallBack(request);
        return Response.getSuccessResponse();
    }

}