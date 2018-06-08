package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 还款方式
 * @author guichang
 * @date 2018/6/8
 */

@Getter
public enum RepayModeEnum {
    /**
     * 过规则
     */
    ONLINE(1, "线上还款"),
    OFFLINE(2, "线下还款");

    private Integer code;
    private String msg;

    RepayModeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
