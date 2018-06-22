package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * @author dengzhenhao
 * @since 2018/6/15 16:19
 */
@Data
public class CheckRepaymentResponse {


    private Long id;

    /**
     * code=2004 时还款状态
     */
    private Byte status;

    /**
     * 确认时间，用于倒计时
     */
    private String confirmTime;


}
