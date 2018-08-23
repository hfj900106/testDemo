package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 校验用户银行卡信息
 *
 * @author wuchengwu
 * @date 2018/8/20
 */
@Data
public class CheckAccountRequest {
    private String phoneNum;
    private String customerName;
    private String accountNo;
    private String bankName;
    private String transactionId;
}