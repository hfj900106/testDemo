package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2018/6/11
 */

@Getter
public enum ProductEnum {
    /**
     * 过规则
     */
    PRODUCT_CODE("101", "印尼APP");

    private String code;
    private String msg;

    ProductEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
