package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.CallbackService;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
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
@RequestMapping("/easy-get")
public class CallbackController {
    @Autowired
    private CallbackService callbackService;

    @ModuleFunc("推送资产-审核回调")
    @PostMapping("/riskCallback/pushBidCallback")
    public Response pushBidCallback(@RequestBody PushBidCallbackRequest request) {
        callbackService.pushBidCallback(request);
        return Response.getSuccessResponse();
    }

}
