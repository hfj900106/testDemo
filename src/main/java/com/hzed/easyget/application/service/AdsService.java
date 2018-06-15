package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
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

    public List<AdsProductResponse> getAdsProductList() {

        ArrayList<AdsProductResponse> AdsProductResponseList = Lists.newArrayList();
        List<AdsProduct> adsProductList = adsProductRepository.getAdsProductList();

        adsProductList.forEach(adsProduct -> {
            AdsProductResponse AdsProductResponse = new AdsProductResponse();
            AdsProductResponse.setTitle(adsProduct.getTitle());
            AdsProductResponse.setImgUrl(adsProduct.getImgUrl());
            AdsProductResponse.setLinkUrl(adsProduct.getLinkUrl());
            AdsProductResponse.setAmountInterval(adsProduct.getAmountInterval());
            AdsProductResponse.setWeights(adsProduct.getWeights());
            AdsProductResponse.setUpTime(DateUtil.localDateTimeToStr2(adsProduct.getUpTime()));
            AdsProductResponse.setDownTime(DateUtil.localDateTimeToStr2(adsProduct.getDownTime()));
            AdsProductResponseList.add(AdsProductResponse);
        });

        return AdsProductResponseList;
    }
}