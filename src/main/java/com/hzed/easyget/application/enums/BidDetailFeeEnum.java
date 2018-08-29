package com.hzed.easyget.application.enums;

import lombok.Getter;
/**
 * 标的费用明细枚举类
 * @author wuchengwu
 * @date 2018/8/22
 */
@Getter
public enum BidDetailFeeEnum {
    /**
     * 过规则
     */
    AUTH_TYPE(1, "认证费用"),
    REVIEW_TYPE(2, "审核费用"),
    HANDLING_TYPE(3, "手续费用");

    private Integer code;
    private String msg;

    BidDetailFeeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
