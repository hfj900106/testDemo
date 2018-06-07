package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.LoanService;
import com.hzed.easyget.controller.model.LoanDetailRequest;
import com.hzed.easyget.controller.model.LoanDetailResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 借款相关
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@ExceptionAnno
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @ModuleFunc("借款详情")
    @RequestMapping("/loanDetail")
    public Response<LoanDetailResponse> loanDetail (@RequestBody LoanDetailRequest request){

        return Response.getSuccessResponse(loanService.loanDetail(request));
    }
}