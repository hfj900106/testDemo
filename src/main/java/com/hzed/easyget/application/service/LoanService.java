package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.ProductEnum;
import com.hzed.easyget.controller.model.LoanDetailRequest;
import com.hzed.easyget.controller.model.LoanDetailResponse;
import com.hzed.easyget.controller.model.SubmitLoanRequest;
import com.hzed.easyget.controller.model.SubmitLoanResponse;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.UserLoanVisitRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserBank;
import com.hzed.easyget.persistence.auto.entity.UserLoanVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 借款相关接口
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
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

    public LoanDetailResponse loanDetail(LoanDetailRequest request) {
        LoanDetailResponse loanDetailResponse = new LoanDetailResponse();
        Bid bid = bidRepository.findByIdWithExp(request.getBid());
        Byte status = bid.getStatus();
        loanDetailResponse.setApplyAmount(bid.getApplyAmount().toString());
        loanDetailResponse.setApplyTime(DateUtil.localDateTimeToStr2(bid.getCreateTime()));
        loanDetailResponse.setInBank(bid.getInBank());
        loanDetailResponse.setInAccount(bid.getInAccount());
        loanDetailResponse.setStatus(status);
        LocalDateTime auditTime = DateUtil.addMins(bid.getCreateTime(), systemProp.getExpectedAuditTimeInterval().intValue());
        loanDetailResponse.setAuditTime(DateUtil.localDateTimeToStr2(auditTime));
        loanDetailResponse.setLoanTime(DateUtil.localDateTimeToStr2(DateUtil.addMins(auditTime,systemProp.getExpectedLendingTimeInterval().intValue())));

        if (BidStatusEnum.AUDIT_FAIL.equals(status) || BidStatusEnum.AUDIT_PASS.equals(status) || BidStatusEnum.REPAYMENT.equals(status)) {
            UserLoanVisit userLoanVisit = new UserLoanVisit();
            userLoanVisit.setId(IdentifierGenerator.nextId());
            userLoanVisit.setUserId(RequestUtil.getGlobalUser().getUserId());
            userLoanVisit.setBidId(bid.getId());
            userLoanVisit.setBidStatus(status);
            userVisitRecordRepository.insert(userLoanVisit);

        }
        return loanDetailResponse;
    }

    public SubmitLoanResponse submitLoan(SubmitLoanRequest request) {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        if (!comService.isLoan(userId)) {
            throw new ComBizException(BizCodeEnum.BID_EXISTS);
        }
        Bid bid = new Bid();
        Long bidId = IdentifierGenerator.nextId();
        bid.setId(bidId);
        bid.setUserId(userId);
        bid.setBidNo(String.valueOf(IdentifierGenerator.nextId()));
        bid.setTitle("消费贷");
        bid.setProductCode(ProductEnum.PRODUCT_CODE.getCode());
        bid.setApplyAmount(request.getApplyAmount());
        bid.setPeriod(request.getPeriod());
        bid.setInBank(request.getInBank());
        bid.setInAccount(request.getInAccount());
        bid.setPurposeCode(request.getPurposeId());
        bid.setClient((byte) 1);
        bid.setStatus(BidStatusEnum.RISK_ING.getCode().byteValue());

        UserBank userBank = new UserBank();
        userBank.setId(IdentifierGenerator.nextId());
        userBank.setInBank(request.getInBank());
        userBank.setInAccount(request.getInAccount());
        bidRepository.insertBidAndUserBank(bid, userBank);

        return SubmitLoanResponse.builder().bid(bidId).build();
    }
}