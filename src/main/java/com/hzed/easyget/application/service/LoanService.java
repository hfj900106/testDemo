package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.enums.BidEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.CheckAccountStatusEnum;
import com.hzed.easyget.application.enums.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 借款相关接口
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Slf4j
@Service
public class LoanService {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private ComService comService;
    @Autowired
    private UserLoanVisitRepository userVisitRecordRepository;
    @Autowired
    private UserBankRepository userBankRepository;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private RiskService riskService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BluePayService bluePayService;

    public LoanDetailResponse loanDetail(LoanDetailRequest request) {
        LoanDetailResponse loanDetailResponse = new LoanDetailResponse();
        Bid bid = bidRepository.findByIdWithExp(request.getBid());
        Byte status = bid.getStatus();
        AbstractProduct product = ProductFactory.getProduct(com.hzed.easyget.application.service.product.ProductEnum.EasyGet).createProduct(bid.getApplyAmount(), bid.getPeriod());
        loanDetailResponse.setApplyAmount(product.getArrivalAmount().toString());
        loanDetailResponse.setApplyTime(DateUtil.localDateTimeToStr1(bid.getCreateTime()));
        loanDetailResponse.setInBank(bid.getInBank());
        loanDetailResponse.setInAccount(bid.getInAccount());
        loanDetailResponse.setStatus(status);
        LocalDateTime auditTime = DateUtil.addMins(bid.getCreateTime(), systemProp.getExpectedAuditTimeInterval().intValue());
        loanDetailResponse.setAuditTime(DateUtil.localDateTimeToStr1(auditTime));
        loanDetailResponse.setLoanTime(DateUtil.localDateTimeToStr1(DateUtil.addMins(auditTime, systemProp.getExpectedLendingTimeInterval().intValue())));

        if (BidStatusEnum.AUDIT_FAIL.getCode().equals(Integer.valueOf(status)) || BidStatusEnum.AUDIT_PASS.getCode().equals(Integer.valueOf(status)) || BidStatusEnum.REPAYMENT.getCode().equals(Integer.valueOf(status))) {
            UserLoanVisit userLoanVisit = new UserLoanVisit();
            userLoanVisit.setId(IDGenerator.nextId());
            userLoanVisit.setUserId(RequestUtil.getGlobalUser().getUserId());
            userLoanVisit.setBidId(bid.getId());
            userLoanVisit.setBidStatus(status);
            userVisitRecordRepository.insert(userLoanVisit);

        }
        return loanDetailResponse;
    }

    public SubmitLoanResponse submitLoan(SubmitLoanRequest request) {
        SubmitLoanResponse submitLoanResponse = new SubmitLoanResponse();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        User user = userRepository.findById(userId);
        // 调bluePay
        PayResponse response = bluePayService.checkAccount(request.getInBank(), request.getInAccount(), user.getMobileAccount(), user.getRealName());
        String status = JSON.parseObject(response.getData()).getString("status");
        if (!CheckAccountStatusEnum.OK.getKey().equals(status)) {
            if (CheckAccountStatusEnum.BANK_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.BANK_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.CUSTOMER_NAME_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.CUSTOMER_NAME_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.ACCOUNT_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.ACCOUNT_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.ACCOUNT_ERROR.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.ACCOUNT_ERROR);
            } else {
                throw new WarnException(BizCodeEnum.SYSTEM_ERROR);
            }
        }
        log.info("================申请借款=======校验用户银行卡信息通过===================");
        // 调风控
        riskService.checkRiskEnableBorrow(user.getMobileAccount(), RequestUtil.getGlobalHead().getImei(), "1");

        Bid bid = new Bid();
        Long bidId = IDGenerator.nextId();
        bid.setId(bidId);
        bid.setUserId(userId);
        bid.setBidNo(String.valueOf(IDGenerator.nextId()));
        bid.setTitle("消费贷");
        bid.setProductCode(ProductEnum.PRODUCT_CODE.getCode());
        bid.setApplyAmount(request.getApplyAmount());
        bid.setPeriod(request.getPeriod());
        bid.setInBank(request.getInBank());
        bid.setInAccount(request.getInAccount());
        bid.setPurposeCode(request.getPurposeId());
        bid.setClient(BidEnum.INDONESIA_APP.getCode());
        bid.setStatus(BidStatusEnum.RISK_ING.getCode().byteValue());
        submitLoanResponse.setBid(bidId);
        // 用户银行卡信息不存在则插入
        List<UserBank> userBankList = userBankRepository.findByUserIdAndInbankAndInAccount(userId, request.getInBank(), request.getInAccount());
        if (ObjectUtils.isEmpty(userBankList)) {
            UserBank userBank = new UserBank();
            userBank.setId(IDGenerator.nextId());
            userBank.setUserId(userId);
            userBank.setInBank(request.getInBank());
            userBank.setInAccount(request.getInAccount());
            bidRepository.insertBidAndUserBank(bid, userBank);
            return submitLoanResponse;
        }

        bidRepository.insertBid(bid);

        return submitLoanResponse;
    }

    public PreSubmitLoanResponse preSubmitLoan(PreSubmitLoanRequest request) {
        PreSubmitLoanResponse subLoanResponse = new PreSubmitLoanResponse();

        BigDecimal loanAmount = request.getLoanAmount();
        Integer period = request.getPeriod();

        Long userId = RequestUtil.getGlobalUser().getUserId();
        List<UserBank> userBankList = userBankRepository.findByUserId(userId);
        if (!ObjectUtils.isEmpty(userBankList)) {
            Dict dict = dictRepository.findByCodeAndLanguage(userBankList.get(0).getInBank().toUpperCase(), RequestUtil.getGlobalHead().getI18n());
            if (!ObjectUtils.isEmpty(dict)) {
                subLoanResponse.setBankCode(dict.getDicCode());
                subLoanResponse.setBankName(dict.getDicValue());
                subLoanResponse.setInAccount(userBankList.get(0).getInAccount());
            }
        }

        AbstractProduct productInfo = ProductFactory.getProduct(com.hzed.easyget.application.service.product.ProductEnum.EasyGet).createProduct(loanAmount, period);

        subLoanResponse.setTotalAmount(productInfo.getTotalRepaymentAmount());
        BigDecimal headFee = productInfo.getHeadFee();
        subLoanResponse.setCost(headFee);
        subLoanResponse.setReceiveAmount(loanAmount.subtract(headFee));
        subLoanResponse.setPeriod(period);
        subLoanResponse.setLoanAmount(loanAmount);
        return subLoanResponse;
    }
}