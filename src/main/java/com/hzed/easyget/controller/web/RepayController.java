package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.RepayListResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 还款
 *
 * @author wuchengwu
 * @date 2018/6/4
 */
@ExceptionAnno
@RestController
@RequestMapping("/repay")
public class RepayController {

    @Autowired
    private RepayService repayService;

    @ModuleFunc("还款列表")
    @GetMapping("/repaidList")
    public Response<RepayListResponse> repaidList(){

        return Response.getSuccessResponse(repayService.repaidList());
    }
}