package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.BillLedgerItemEnum;
import com.hzed.easyget.application.enums.BillStatusEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.BillLedgerRepository;
import com.hzed.easyget.infrastructure.repository.BillRepository;
import com.hzed.easyget.infrastructure.repository.UserTokenRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;
import com.hzed.easyget.persistence.auto.entity.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 一些公用的方法
 *
 * @author guichang
 * @since 2018/5/24
 */
@Slf4j
@Service
public class ComService {
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private BillLedgerRepository billLedgerRepository;

    /**
     * 校验token参数
     */
    public void validateToken(GlobalHead globalHeadr) {
        String imei = globalHeadr.getImei();
        String token = globalHeadr.getToken();
        if (StringUtils.isBlank(token)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_TOKEN);
        }

        GlobalUser globalUser = JwtUtil.verify(token, GlobalUser.class);
        if (globalUser == null) {
            throw new WarnException(BizCodeEnum.ILLEGAL_TOKEN);
        }
        Long userId = globalUser.getUserId();

        String tokenCacheKey = RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei;
        String tokenCache = redisService.getCache(tokenCacheKey);

        if (StringUtils.isBlank(tokenCache)) {
            // 检查库中token是否失效，未失效则放入token
            UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
            if (userToken == null) {
                throw new WarnException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (!token.equals(userToken.getToken())) {
                throw new WarnException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (LocalDateTime.now().compareTo(userToken.getExpireTime()) > 0) {
                throw new WarnException(BizCodeEnum.TOKEN_EXPIRE);
            } else {
                //刷新Redis缓存
                redisService.setCache(tokenCacheKey, userToken.getToken(), 10800L);
            }
        } else if (!token.equals(tokenCache)) {
            // 检查redis token是否与传过来的一致
            throw new WarnException(BizCodeEnum.ILLEGAL_TOKEN);
        }
    }

    /**
     * 获取标的待还总费用
     *
     * @param bidId             标id
     * @param realRepaymentTime 实际还款时间
     */
    public BigDecimal getBidNoRepayFee(Long bidId, LocalDateTime realRepaymentTime) {
        bidRepository.findByIdWithExp(bidId);

        BigDecimal total = BigDecimal.ZERO;
        Long billId = billRepository.findAllBillByBidIdWithExp(bidId).get(0).getId();
        // TODO 目前不做多期
        List<BillLedger> ledgers = billLedgerRepository.findAllBillLedgerByBillIdWithExp(billId);
        // 台账中是否有逾期费标志
        boolean overdueFlag = false;
        for (BillLedger ledger : ledgers) {
            total = total.add(getBillItemNoRepayFee(ledger.getBillId(), ledger.getRepaymentItem().intValue(), realRepaymentTime));
            if (BillLedgerItemEnum.OVERDUE_FEE.getCode().intValue() == ledger.getRepaymentItem().intValue()) {
                overdueFlag = true;
            }
        }
        // 单独处理逾期费
        if (!overdueFlag) {
            total = total.add(getBillItemNoRepayFee(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode(), realRepaymentTime));
        }
        return total;
    }

    /**
     * 获取账单台账待还费用
     *
     * @param billId            账单id
     * @param item              台账类型枚举值 如 BillLedgerItemEnum.OVERDUE_FEE.getCode()
     * @param realRepaymentTime 实际还款时间
     */
    public BigDecimal getBillItemNoRepayFee(Long billId, Integer item, LocalDateTime realRepaymentTime) {
        Bill bill = billRepository.findByIdWithExp(billId);
        int status = bill.getStatus().intValue();
        // 账单已结清则没有逾期费
        if (BillStatusEnum.NORMAL_CLEAR.getCode().equals(status) ||
                BillStatusEnum.OVERDUE_CLEAR.getCode().equals(status)) {
            return BigDecimal.ZERO;
        }
        // 逾期费另外计算
        if (BillLedgerItemEnum.OVERDUE_FEE.getCode().equals(item)) {
            return getBillOverFeeNoRepay(billId, realRepaymentTime);
        }

        BillLedger ledger = billLedgerRepository.findBillLedgerItemByBillIdWithExp(billId, item.byteValue());
        // 应还金额
        BigDecimal repaymentAmount = ledger.getRepaymentAmount();
        // 实还金额
        BigDecimal realRepaymentAmount = ledger.getRealRepaymentAmount();

        return realRepaymentAmount == null ? repaymentAmount : repaymentAmount.subtract(realRepaymentAmount);
    }

    /**
     * 获取账单的待还的逾期费
     *
     * @param billId            账单id
     * @param realRepaymentTime 实还时间
     */
    public BigDecimal getBillOverFeeNoRepay(Long billId, LocalDateTime realRepaymentTime) {
        Bill bill = billRepository.findByIdWithExp(billId);
        int status = bill.getStatus().intValue();
        // 账单已结清则没有逾期费
        if (BillStatusEnum.NORMAL_CLEAR.getCode().equals(status) ||
                BillStatusEnum.OVERDUE_CLEAR.getCode().equals(status)) {
            return BigDecimal.ZERO;
        }

        // 逾期天数
        int overDays = DateUtil.getBetweenDays(bill.getRepaymentTime(), realRepaymentTime);
        // 逾期天数小于0则没有逾期费
        if (overDays <= 0) {
            return BigDecimal.ZERO;
        }

        Bid bid = bidRepository.findByIdWithExp(bill.getBidId());

        AbstractProduct product = ProductFactory.getProduct(ProductEnum.EasyGet).createProduct(bid.getLoanAmount(), bid.getPeriod());
        // 总逾期费
        BigDecimal allOverFee = product.getOverFee(overDays);
        // 已还逾期费
        BillLedger overDueLedger = billLedgerRepository.findBillLedgerItemByBillId(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().byteValue());

        return overDueLedger == null ? allOverFee : allOverFee.subtract(overDueLedger.getRealRepaymentAmount());
    }

    /**
     * 根据bid状态判断用户是否有贷款资格
     */
    public boolean isLoan(Long userId) {
        List<Bid> bidList = bidRepository.findByUserIdAndStatus(userId, Lists.newArrayList(BidStatusEnum.RISK_ING.getCode().byteValue(), BidStatusEnum.MANMADE_ING.getCode().byteValue(),
                BidStatusEnum.AUDIT_PASS.getCode().byteValue(), BidStatusEnum.REPAYMENT.getCode().byteValue()));
        if (ObjectUtils.isEmpty(bidList)) {
            return true;
        }
        return false;
    }


}