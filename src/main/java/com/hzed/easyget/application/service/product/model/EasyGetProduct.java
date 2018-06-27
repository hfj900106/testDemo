package com.hzed.easyget.application.service.product.model;

import com.hzed.easyget.infrastructure.utils.Arith;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 印尼产品
 *
 * @author guichang
 * @date 2018/6/2
 */

@Data
@AllArgsConstructor
public class EasyGetProduct implements AbstractProduct {
    private BigDecimal amount;
    private Integer time;

    /**
     * 砍头息费率
     */
    private BigDecimal headFeeRate;
    /**
     * 尾款费率
     */
    private BigDecimal tailFeeRate;
    /**
     * 逾期费率
     */
    private BigDecimal overFeeRate;

    /**
     * 最小还款金额
     */
    private BigDecimal minRepayAmount;

    public EasyGetProduct(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal getHeadFee() {
        return amount.multiply(headFeeRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getInerest() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTailFee() {
        return amount.multiply(tailFeeRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getTotalRepaymentAmount() {
        return amount.add(getTailFee());
    }

    @Override
    public BigDecimal getOverFee(Integer overDay) {
        return Arith.mul(amount, new BigDecimal(overDay), overFeeRate.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public BigDecimal getMinRepayAmount() {
        return minRepayAmount;
    }
}