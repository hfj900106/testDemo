package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2018/6/11
 */

@Getter
public enum ProductTypeEnum {
    /**
     * 过规则
     */
    PRODUCT_CODE("101", "印尼APP");

    private String code;
    private String msg;

    ProductTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
