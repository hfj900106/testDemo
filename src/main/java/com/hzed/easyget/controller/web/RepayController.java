package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /** TODO */
    @ModuleFunc("结清全部")
    @GetMapping("/repayAll")
    public Response repayAll(@RequestBody RepayAllRequest request){
        repayService.repayAll(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("部分还款")
    @GetMapping("/repayPart")
    public Response repayPart(@RequestBody RepayPartRequest request){
        repayService.repayPart(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("还款详情")
    @GetMapping("/repayDetail")
    public Response<RepayDetailResponse> repayDetail(@RequestBody RepayDetailRequest request){

        return Response.getSuccessResponse(repayService.repayDetail(request));
    }


}