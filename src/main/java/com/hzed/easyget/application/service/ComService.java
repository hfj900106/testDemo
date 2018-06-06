package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BillLedgerItemEnum;
import com.hzed.easyget.application.enums.BillStatusEnum;
import com.hzed.easyget.application.service.product.model.EasyGetPruduct;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 一些公用的方法
 *
 * @author guichang
 * @since 2018/5/24
 */

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
     * 校验请求头非token参数
     */
    public void validateHeader(GlobalHeadr globalHeadr) {
        if (StringUtils.isBlank(globalHeadr.getAppKey())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_APPKEY);
        }
        if (StringUtils.isBlank(globalHeadr.getPlatform())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PLATFORM);
        }
        if (StringUtils.isBlank(globalHeadr.getVersion())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_VERSION);
        }
        if (StringUtils.isBlank(globalHeadr.getI18n())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_I18N);
        }
        if (StringUtils.isBlank(globalHeadr.getImei())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_IMEI);
        }
    }

    /**
     * 校验token参数
     */
    public void validateToken(GlobalHeadr globalHeadr) {
        String imei = globalHeadr.getImei();
        String token = globalHeadr.getToken();
        if (StringUtils.isBlank(token)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }

        GlobalUser globalUser = JwtUtil.verify(token, GlobalUser.class);
        if (globalUser == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }
        Long userId = globalUser.getUserId();

        String tokenCacheKey = RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei;
        String tokenCache = redisService.getCache(tokenCacheKey);

        if (StringUtils.isBlank(tokenCache)) {
            // 检查库中token是否失效，未失效则放入token
            UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
            if (userToken == null) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (!token.equals(userToken.getToken())) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (LocalDateTime.now().compareTo(userToken.getExpireTime()) > 0) {
                throw new ComBizException(BizCodeEnum.TOKEN_EXPIRE);
            } else {
                redisService.setCache(tokenCacheKey, userToken.getToken(), RedisConsts.THREE_HOUR);
            }
        } else if (!token.equals(tokenCache)) {
            // 检查redis token是否与传过来的一致
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }
    }

    /**
     * 获取账单台账待还费用
     *
     * @param billId   账单id
     * @param itemEnum 台账类型
     */
    public BigDecimal getBillItemNoRepay(Long billId, BillLedgerItemEnum itemEnum) {
        Bill bill = billRepository.findById(billId);
        if (bill == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + " 不存在");
        }
        int status = bill.getStatus().intValue();
        // 账单已结清则没有逾期费
        if (BillStatusEnum.NORMAL_CLEAR.getCode().equals(status) ||
                BillStatusEnum.OVERDUE_CLEAR.getCode().equals(status)) {
            return BigDecimal.ZERO;
        }
        // 逾期费另外计算
        if (BillLedgerItemEnum.OVERDUE_FEE.equals(itemEnum)) {
            return getBillOverFeeNoRepay(billId);
        }

        BillLedger ledger = billLedgerRepository.findBillLedgerItemByBillId(billId, itemEnum.getCode().byteValue());
        if (ledger == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + "的台账类型：" + itemEnum.getCode().byteValue() + "不存在");
        }
        // 应还金额
        BigDecimal repaymentAmount = ledger.getRepaymentAmount();
        // 实还金额
        BigDecimal realRepaymentAmount = ledger.getRealRepaymentAmount();

        return realRepaymentAmount == null ? repaymentAmount : repaymentAmount.subtract(realRepaymentAmount);
    }

    /**
     * 获取账单的待还的逾期费
     *
     * @param billId 账单id
     */
    public BigDecimal getBillOverFeeNoRepay(Long billId) {
        Bill bill = billRepository.findById(billId);
        if (bill == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + " 不存在");
        }
        int status = bill.getStatus().intValue();
        // 账单已结清则没有逾期费
        if (BillStatusEnum.NORMAL_CLEAR.getCode().equals(status) ||
                BillStatusEnum.OVERDUE_CLEAR.getCode().equals(status)) {
            return BigDecimal.ZERO;
        }

        // 逾期天数
        int overDays = DateUtil.daysBetween(bill.getRepaymentTime(), LocalDateTime.now());
        // 逾期天数小于0则没有逾期费
        if (overDays <= 0) {
            return BigDecimal.ZERO;
        }

        Bid bid = bidRepository.findById(bill.getBidId());
        if (bill == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + " 对应的标的不存在");
        }

        EasyGetPruduct product = new EasyGetPruduct(bid.getLoanAmount(), bid.getPeriod());
        // 总逾期费
        BigDecimal allOverFee = product.getOverFee(overDays);
        // 已还逾期费
        BillLedger overDueLedger = billLedgerRepository.findBillLedgerItemByBillId(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().byteValue());

        return overDueLedger == null ? allOverFee : allOverFee.subtract(overDueLedger.getRealRepaymentAmount());
    }

}