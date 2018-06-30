package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 标的枚举类
 * @author wuchengwu
 * @date 2018/6/27
 */
@Getter
public enum BidEnum {
    /**
     * 过规则
     */
    INDONESIA_APP("Rupiah Get", "IndonesiaApp");

    private String code;
    private String msg;

    BidEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
