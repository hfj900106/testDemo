package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BidDetailFeeEnum;
import com.hzed.easyget.application.enums.BidEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.ProductTypeEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.SystemProp;
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
        AbstractProduct product = ProductFactory.getProduct().createProduct(bid.getApplyAmount(), bid.getPeriod());
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
        String inBank = request.getInBank();
        String inAccount = request.getInAccount();

        SubmitLoanResponse submitLoanResponse = new SubmitLoanResponse();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        User user = userRepository.findById(userId);
        // 调bluePay
       /* PayResponse response = bluePayService.checkAccount(request.getInBank(), request.getInAccount(), user.getMobileAccount(), user.getRealName());
        String status = JSON.parseObject(response.getData()).getString("status");
        if (!CheckAccountStatusEnum.OK.getKey().equals(status)) {
            log.error("银行卡信息校验失败");
            if (CheckAccountStatusEnum.BANK_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.BANK_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.CUSTOMER_NAME_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.CUSTOMER_NAME_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.ACCOUNT_NOT_EXISTS.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.ACCOUNT_NOT_EXISTS);
            } else if (CheckAccountStatusEnum.ACCOUNT_ERROR.getKey().equals(status)) {
                throw new WarnException(BizCodeEnum.ACCOUNT_ERROR);
            } else {
                throw new WarnException(BizCodeEnum.CHECK_ACCOUNT_ERROR);
            }
        }
        log.info("================申请借款=======校验用户银行卡信息通过===================");*/
        // 调风控
        riskService.checkRiskEnableBorrow(user.getMobileAccount(), RequestUtil.getGlobalHead().getImei(), "1");

        Bid bid = new Bid();
        Long bidId = IDGenerator.nextId();
        bid.setId(bidId);
        bid.setUserId(userId);
        bid.setBidNo(String.valueOf(IDGenerator.nextId()));
        bid.setTitle("消费贷");
        bid.setProductCode(ProductTypeEnum.PRODUCT_CODE.getCode());
        bid.setApplyAmount(request.getApplyAmount());
        bid.setPeriod(request.getPeriod());
        bid.setInBank(inBank);
        bid.setInAccount(inAccount);
        bid.setPurposeCode(request.getPurposeId());
        bid.setClient(BidEnum.INDONESIA_APP.getCode());
        bid.setStatus(BidStatusEnum.RISK_ING.getCode().byteValue());
        submitLoanResponse.setBid(bidId);
        // 借款费用详情列表
        List<BidDetailFee> bidDetailFeeList = getBidDetailFeeList(request, bidId);
        // 用户银行卡信息不存在则插入
        List<UserBank> userBankList = userBankRepository.findByUserIdAndInbankAndInAccount(userId, inBank, inAccount);
        if (ObjectUtils.isEmpty(userBankList)) {
            UserBank userBank = new UserBank();
            userBank.setId(IDGenerator.nextId());
            userBank.setUserId(userId);
            userBank.setInBank(inBank);
            userBank.setInAccount(inAccount);
            bidRepository.insertBidAndUserBankAndBidDetailFee(bid, userBank, bidDetailFeeList);
            return submitLoanResponse;
        }

        bidRepository.insertBidAndBidDetailFee(bid, bidDetailFeeList);

        return submitLoanResponse;
    }

    private List<BidDetailFee> getBidDetailFeeList(SubmitLoanRequest request, Long bidId) {
        List<BidDetailFee> bidDetailFeeList = Lists.newArrayList();

        BidDetailFee authFee = new BidDetailFee();
        authFee.setId(IDGenerator.nextId());
        authFee.setBidId(bidId);
        authFee.setFeeType(BidDetailFeeEnum.AUTH_TYPE.getCode().byteValue());
        authFee.setFee(request.getAuthFee());

        BidDetailFee reviewFee = new BidDetailFee();
        reviewFee.setId(IDGenerator.nextId());
        reviewFee.setBidId(bidId);
        reviewFee.setFeeType(BidDetailFeeEnum.REVIEW_TYPE.getCode().byteValue());
        reviewFee.setFee(request.getReviewFee());

        BidDetailFee handlingFee = new BidDetailFee();
        handlingFee.setId(IDGenerator.nextId());
        handlingFee.setBidId(bidId);
        handlingFee.setFeeType(BidDetailFeeEnum.HANDLING_TYPE.getCode().byteValue());
        handlingFee.setFee(request.getHandlingFee());

        bidDetailFeeList.add(authFee);
        bidDetailFeeList.add(reviewFee);
        bidDetailFeeList.add(handlingFee);

        return bidDetailFeeList;
    }

    public PreSubmitLoanResponse preSubmitLoan(PreSubmitLoanRequest request) {
        PreSubmitLoanResponse subLoanResponse = new PreSubmitLoanResponse();

        BigDecimal loanAmount = request.getLoanAmount();
        Integer period = request.getPeriod();
        Long userId = RequestUtil.getGlobalUser().getUserId();

        List<UserBank> userBankList = userBankRepository.findByUserId(userId);
        if (!ObjectUtils.isEmpty(userBankList)) {
            Dict dict = dictRepository.findOneByCodeAndLanguage(userBankList.get(0).getInBank().toUpperCase(), RequestUtil.getGlobalHead().getI18n());
            if (!ObjectUtils.isEmpty(dict)) {
                subLoanResponse.setBankCode(dict.getDicCode());
                subLoanResponse.setBankName(dict.getDicValue());
                subLoanResponse.setInAccount(userBankList.get(0).getInAccount());
            }
        }

        AbstractProduct productInfo = ProductFactory.getProduct().createProduct(loanAmount, period);

        subLoanResponse.setTotalAmount(productInfo.getTotalRepaymentAmount());
        BigDecimal headFee = productInfo.getHeadFee();
        subLoanResponse.setCost(headFee);
        subLoanResponse.setReceiveAmount(productInfo.getArrivalAmount());
        subLoanResponse.setPeriod(period);
        subLoanResponse.setLoanAmount(loanAmount);
        subLoanResponse.setServiceFee(productInfo.getTailFee());

        // 费用明细
        BidDetailFeeResponse bidDetailFeeResponse = new BidDetailFeeResponse();
        bidDetailFeeResponse.setAuthFee(systemProp.getAuthFee());
        bidDetailFeeResponse.setReviewFee(systemProp.getReviewFee());
        bidDetailFeeResponse.setHandlingFee(systemProp.getHandlingFee());

        subLoanResponse.setBidDetailFeeResponse(bidDetailFeeResponse);
        return subLoanResponse;
    }
}