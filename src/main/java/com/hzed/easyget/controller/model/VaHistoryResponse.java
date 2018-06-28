package com.hzed.easyget.controller.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author dengzhenhao
 * @since 2018/6/28 18:14
 */
@Data
@Builder
public class VaHistoryResponse {
    /**
     * va码
     */
    private String va;

    /**
     * 还款方式
     */
    private String mode;
}
