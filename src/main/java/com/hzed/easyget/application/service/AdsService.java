package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.AdsProductListRequest;
import com.hzed.easyget.controller.model.AdsProductResponse;
import com.hzed.easyget.infrastructure.repository.AdsProductRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.persistence.auto.entity.AdsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告相关接口
 *
 * @author wuchengwu
 * @date 2018/6/15
 */
@Service
public class AdsService {

    @Autowired
    private AdsProductRepository adsProductRepository;

    public List<AdsProductResponse> getAdsProductList(AdsProductListRequest request) {
        ArrayList<AdsProductResponse> adsProductResponseList = Lists.newArrayList();

        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();

        List<AdsProduct> adsProductList = adsProductRepository.getAdsProductList(pageNo,pageSize);

        adsProductList.forEach(adsProduct -> {
            AdsProductResponse adsProductResponse = new AdsProductResponse();
            adsProductResponse.setTitle(adsProduct.getTitle());
            adsProductResponse.setImgUrl(adsProduct.getImgUrl());
            adsProductResponse.setLinkUrl(adsProduct.getLinkUrl());
            adsProductResponse.setAmountInterval(adsProduct.getAmountInterval());
            adsProductResponse.setWeights(adsProduct.getWeights());
            adsProductResponse.setUpTime(DateUtil.localDateTimeToStr2(adsProduct.getUpTime()));
            adsProductResponse.setDownTime(DateUtil.localDateTimeToStr2(adsProduct.getDownTime()));
            adsProductResponseList.add(adsProductResponse);
        });

        return adsProductResponseList;
    }
}