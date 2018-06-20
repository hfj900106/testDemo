package com.hzed.easyget.application.service.product;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 产品工厂
 *
 * @author guichang
 * @date 2018/6/2
 */

public class ProductFactory {

    public static final Map<ProductEnum, ProductService> SERVICE_MAP = Maps.newHashMap();

    static {
        SERVICE_MAP.put(ProductEnum.EasyGet, new EasyGetService());
    }

    public static ProductService getProduct(ProductEnum pEnum) {

        ProductService productService = SERVICE_MAP.get(pEnum);
        if (productService == null) {
            throw new IllegalArgumentException("产品实例获取失败");
        }
        return productService;
    }
}
