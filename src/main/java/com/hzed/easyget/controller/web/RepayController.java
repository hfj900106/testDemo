package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 还款
 *
 * @author wuchengwu
 * @date 2018/6/4
 */
@ExceptionAnno
@RestController
@RequestMapping("/api/repay")
public class RepayController {

    @Autowired
    private RepayService repayService;

    @ModuleFunc("还款列表")
    @GetMapping("/repaidList")
    public RepayListResponse repaidList() {
        return repayService.repaidList();
    }

    @ModuleFunc("结清全部")
    @PostMapping("/repayAll")
    public void repayAll(@Valid @RequestBody RepayAllRequest request) {
        repayService.repayAll(request);
    }

    @ModuleFunc("部分还款")
    @PostMapping("/repayPart")
    public void repayPart(@Valid @RequestBody RepayPartRequest request) {
        repayService.repayPart(request);
    }

    @ModuleFunc("还款详情")
    @GetMapping("/repayDetail")
    public RepayDetailResponse repayDetail(@Valid @RequestBody RepayDetailRequest request) {
        return repayService.repayDetail(request);
    }

    @ModuleFunc("部分还款详情")
    @RequestMapping("/repayPartDetail")
    public RepayPartDetailResponse repayPartDetail(@Valid @RequestBody RepayPartDetailRequest request) {
        return repayService.repayPartDetail(request);
    }


}