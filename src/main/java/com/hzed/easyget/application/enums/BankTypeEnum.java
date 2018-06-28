package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 银行类型
 *
 * @author zhangrl
 * @date 2018/6/8
 */
@Getter
public enum BankTypeEnum {
    PERMATA("permata"),
    BNI("bni");

    private String bank;


    BankTypeEnum(String bank) {
        this.bank = bank;
    }
}
