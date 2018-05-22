package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.ProductInfoResponse;
import org.springframework.stereotype.Service;

/**
 * @author wuchengwu
 * @since 2018/5/22
 */
@Service
public class HomeService {

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse ProductInfoResponse = new ProductInfoResponse();

        return ProductInfoResponse;
    }
}