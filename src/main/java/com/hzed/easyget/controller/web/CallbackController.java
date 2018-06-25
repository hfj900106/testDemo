package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.CallbackService;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.head.HeaderIgnore;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 风控回调，无须做国际化
 *
 * @author hfj
 * @date 2018/6/9
 */
@ExceptionAnno
@RestController
@RequestMapping("/callback")
@Slf4j
public class CallbackController {
    @Autowired
    private CallbackService callbackService;

    @ModuleFunc(value = "风控审核回调", isCommonResponse = false)
    @PostMapping("/riskCallback/pushBidCallback")
    @HeaderIgnore
    public PushBidCallbackResponse pushBidCallback(@Valid @RequestBody PushBidCallbackRequest request) {
        return callbackService.pushBidCallback(request);
    }

}
