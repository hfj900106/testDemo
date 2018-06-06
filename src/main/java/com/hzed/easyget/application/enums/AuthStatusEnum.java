package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 */

@Getter
public enum AuthStatusEnum {
    /**
     * 过规则
     */
    UN_AUTH(100, "未认证"),
    HAS_AUTH(200, "已认证"),
    TO_AUTH(201, "认证中"),
    FAIl_AUTH(401, "认证失败");

    private Integer code;
    private String msg;

    AuthStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
