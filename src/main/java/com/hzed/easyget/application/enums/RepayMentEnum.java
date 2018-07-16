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
    OTC("otc", null, "OTC"),
    PERMATA_ATM("atm", "permata", "Permata ATM"),
    MANDIRI_ATM("atm", "mandiri", "Mandiri ATM"),
    BNI_ATM("atm", "bni", "BNI ATM");

    private String blue;
    private String bank;
    private String mode;


    RepayMentEnum(String blue, String bank, String mode) {
        this.blue = blue;
        this.mode = mode;
        this.bank = bank;
    }

    public static String getBlue(String mode) {
        for (RepayMentEnum v : RepayMentEnum.values()) {
            if (v.mode.equals(mode)) {
                return v.blue;
            }
        }
        return null;
    }
    public static String getBank(String mode) {
        for (RepayMentEnum v : RepayMentEnum.values()) {
            if (v.mode.equals(mode)) {
                return v.bank;
            }
        }
        return null;
    }
}
