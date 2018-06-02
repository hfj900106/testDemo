package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author guichang
 */

@Getter
public enum BidStatusEnum {

    AUDIT_ING(0, "审核中"),
    AUDIT_FAIL(1, "审核不通过"),
    AUDIT_PASS(2, "审核通过"),
    REPAYMENT(3, "已放款"),
    CLEARED(4, "已结清")
    ;

    private Integer code;
    private String msg;

    BidStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
