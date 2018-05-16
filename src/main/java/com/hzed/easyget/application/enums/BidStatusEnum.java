package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author guichang
 */

@Getter
public enum BidStatusEnum {

    AUDIT_ING(0, "审核中"),
    AUDIT_PASS(2, "借款中(审核通过)"),
    WAIT_LENDING(3, "待放款"),
    REPAYMENT(4, "还款中(财务放款)"),
    AUDIT_FAIL(-1, "审核不通过"),
    BORROW_AUDIT_FAIL(-2, "借款中不通过"),
    LENDING_FAIL(-3, "放款不通过"),
    CANCEL(-5, "撤销"),
    USER_CONFIRM(-11, "用户确定借款");

    private Integer code;
    private String msg;

    BidStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
