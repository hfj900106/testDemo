package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AdsService;
import com.hzed.easyget.controller.model.AdsProductResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告相关
 *
 * @author wuchengwu
 * @date 2018/6/15
 */
@ExceptionAnno
@RestController
@RequestMapping("/api/ads")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @ModuleFunc("获取广告产品列表")
    @PostMapping("/getAdsProductList")
    public List<AdsProductResponse> getAdsProductList() {
        return adsService.getAdsProductList();
    }
}