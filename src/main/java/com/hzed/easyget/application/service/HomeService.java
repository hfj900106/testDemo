package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.ProductInfoResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.repository.HomeRepository;
import com.hzed.easyget.persistence.auto.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wuchengwu
 * @data 2018/5/22
 */
@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = homeRepository.getProductInfo();
        if(Objects.isNull(product)){
            throw new ComBizException(BizCodeEnum.PRODUT_NOTEXISTS);
        }
        productInfoResponse.setLoanAmount(product.getLoanAmount());
        productInfoResponse.setLoanTime(product.getLoanTime());
        return productInfoResponse;
    }
}