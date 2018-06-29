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
    OTC("otc", "OTC", "OTC还款方式"),
    ATM("atm", "BNI ATM", "ATM还款方式");

    private String blue;
    private String mode;
    private String desc;


    RepayMentEnum(String blue, String mode, String desc) {
        this.blue = blue;
        this.mode = mode;
        this.desc = desc;
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
