package com.hzed.easyget.application.service.product.model;

import java.math.BigDecimal;

/**
 * 暂无描述
 *
 * @author guichang
 * @date 2018/6/22
 */

public interface AbstractProduct {
    /**
     * 砍头息
     *
     * @return 砍头息
     */
    BigDecimal getHeadFee();

    /**
     * 利息
     *
     * @return 利息
     */
    BigDecimal getInterest();

    /**
     * 尾款
     *
     * @return 尾款
     */
    BigDecimal getTailFee();

    /**
     * 总还款金额
     *
     * @return 总还款金额
     */
    BigDecimal getTotalRepaymentAmount();

    /**
     * 逾期费
     *
     * @param overDay 逾期天数
     * @return 逾期费
     */
    BigDecimal getOverFee(Integer overDay);

    /**
     * 最小还款金额
     */
    BigDecimal getMinRepayAmount();

    /**
     * 到账金额
     */
    BigDecimal getArrivalAmount();
}