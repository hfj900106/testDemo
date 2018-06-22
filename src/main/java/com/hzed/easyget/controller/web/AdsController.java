package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AdsService;
import com.hzed.easyget.controller.model.AdsProductListRequest;
import com.hzed.easyget.controller.model.AdsProductResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 广告相关
 *
 * @author wuchengwu
 * @date 2018/6/15
 */
@Api(value="广告" ,tags = "广告",description = "广告相关接口")
@ExceptionAnno
@RestController
@RequestMapping("/api/ads")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @ApiOperation(value="获取广告产品列表")
    @ModuleFunc("获取广告产品列表")
    @PostMapping("/getAdsProductList")
    public List<AdsProductResponse> getAdsProductList(@Valid @RequestBody AdsProductListRequest request) {
        return adsService.getAdsProductList(request);
    }
}