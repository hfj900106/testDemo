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
    /**
     * 过规则
     */
    PERMATA("permata"),
    BNI("bni");

    private String bank;


    BankTypeEnum(String bank) {
        this.bank = bank;
    }
}
