package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 获取va码响应实体
 */
@Data
public class TransactionVAResponse {
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
