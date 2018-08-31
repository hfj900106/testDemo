package com.hzed.easyget.application.service.product;

import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guichang
 */
public interface ProductService {
    /**
     * 根据标的创建账单
     *
     * @param bid 标的
     * @return 账单列表
     */
    List<Bill> createBills(Bid bid);

    /**
     * 根据标的创建台账
     * @param bills 账单列表
     * @param amount 借款金额
     * @param period 期限
     * @return 台账列表
     */
    List<BillLedger> createBillLedger(List<Bill> bills, BigDecimal amount, Integer period);

    /**
     * 校验标的是否为当前产品
     *
     * @param bid 标的
     */
    void checkBid(Bid bid);

    /**
     * 创建产品，主要是一些费用信息
     *
     * @param amount 借款金额
     * @param days   借款天数
     * @return 产品
     */
    AbstractProduct createProduct(BigDecimal amount, Integer days);

    /**
     * 获取应还时间
     * @param period 借款天数
     * @return 应还时间
     */
    LocalDateTime getRepaymentTime(Integer period);
}
