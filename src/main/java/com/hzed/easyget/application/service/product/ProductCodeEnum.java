package com.hzed.easyget.application.service.product;

import lombok.Getter;

/**
 * 产品码枚举类
 *
 * @author hfj
 * @data 2018/06/20
 */
@Getter
public enum ProductCodeEnum {

    /**
     * 印尼产品
     */
    INDONESIA("101", "印尼产品");

    /**
     * 产品代码
     */
    private String code;
    /**
     *  产品信息
     */
    private String msg;

    ProductCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
