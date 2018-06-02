package com.hzed.easyget.application.service.product.model;

import com.hzed.easyget.infrastructure.utils.Arith;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 印尼产品
 *
 * @author guichang
 * @date 2018/6/2
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyGetPruduct {
    private BigDecimal amount;
    private Integer time;

    public EasyGetPruduct(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 砍头息
     */
    public BigDecimal getHeadFee() {
        return amount.multiply(new BigDecimal(0.15));
    }

    /**
     * 尾款
     */
    public BigDecimal getTailFee() {
        return amount.multiply(new BigDecimal(0.06));
    }

    /**
     * 总还款金额
     */

    public BigDecimal getRepaymentAmount() {
        return amount.add(getTailFee());
    }
    /**
     * 逾期费
     * @param overDay 逾期天数
     */
    public BigDecimal getOverFee(Integer overDay) {
        return Arith.mul(amount, new BigDecimal(overDay), new BigDecimal(0.02));
    }


}