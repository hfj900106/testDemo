package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author guichang
 */

@Getter
public enum BidStatusEnum {

    /** 过规则 */
    RISK_ING(1, "待走风控"),
    MANMADE_ING(2, "待人审"),
    AUDIT_FAIL(3, "审核不通过"),
    AUDIT_PASS(4, "审核通过"),
    REPAYMENT(5, "已放款"),
    CLEARED(6, "已结清");

    private Integer code;
    private String msg;

    BidStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
