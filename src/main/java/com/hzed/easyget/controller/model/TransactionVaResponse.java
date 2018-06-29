package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 还款码响应实体
 *
 * @author wuchengwu
 * @date 2018/6/4
 */
@Data
public class TransactionVaResponse {
    /**
     * VA码
     */
    private String vaCodel;
    /**
     * va码创建时间
     */
    private long expireTime;
    /**
     * 交易方式
     */
    private String mode;
}
