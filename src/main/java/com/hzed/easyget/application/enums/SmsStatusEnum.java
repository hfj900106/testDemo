package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author hfj
 */

@Getter
public enum SmsStatusEnum {
    /**
     * 短信状态  1-处理中 2-发送成功 3-发送失败
     */
    PROCESSING(1, "处理中"),
    SUCCESS(2, "发送成功"),
    FAIL(3, "发送失败");

    private Integer code;
    private String msg;

    SmsStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
