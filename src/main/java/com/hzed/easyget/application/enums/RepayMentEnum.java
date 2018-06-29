package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 交易方式
 *
 * @author zhangrl
 * @date 2018/6/8
 */
@Getter
public enum RepayMentEnum {

    /**
     * 过规则
     */
    OTC("otc", "OTC"),
    ATM("atm", "ATM");

    private String blue;
    private String mode;


    RepayMentEnum(String blue, String mode) {
        this.blue = blue;
        this.mode = mode;
    }

    public static String getBlue(String mode) {
        for (RepayMentEnum v : RepayMentEnum.values()) {
            if (v.mode.equals(mode)) {
                return v.blue;
            }
        }
        return null;
    }
}
