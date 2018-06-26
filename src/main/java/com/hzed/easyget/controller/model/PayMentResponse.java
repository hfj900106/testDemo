package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 刷新还款状态返回实体
 *
 * @author zhangrl
 * @date 2018/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayMentResponse {
    /**
     * 交易流水id(非交易id)
     */
    private Long payId;
    /**
     * 还款状态
     */
    private String status;
    /**
     * 标的id
     */
    private Long bidId;
}
