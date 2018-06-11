package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.controller.model.LoanDetailRequest;
import com.hzed.easyget.controller.model.LoanDetailResponse;
import com.hzed.easyget.controller.model.SubmitLoanRequest;
import com.hzed.easyget.controller.model.SubmitLoanResponse;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public LoanDetailResponse loanDetail(LoanDetailRequest request) {
        LoanDetailResponse loanDetailResponse = new LoanDetailResponse();
        String bidStr = request.getBid();
        Long bidId = Long.valueOf(bidStr);
        Bid bid = bidRepository.findByIdWithExp(bidId);
        loanDetailResponse.setApplyAmount(bid.getApplyAmount().toString());
        loanDetailResponse.setApplyTime(DateUtil.localDateTimeToStr2(bid.getCreateTime()));
        loanDetailResponse.setInBank(bid.getInBank());
        loanDetailResponse.setInAccount(bid.getInAccount());
        LocalDateTime auditTime = DateUtil.addMins(bid.getCreateTime(), systemProp.getExpectedAuditTimeInterval().intValue());
        loanDetailResponse.setAuditTime(DateUtil.localDateTimeToStr2(auditTime));
        loanDetailResponse.setLoanTime(DateUtil.localDateTimeToStr2(DateUtil.addMins(auditTime,systemProp.getExpectedLendingTimeInterval().intValue())));
        return loanDetailResponse;
    }

    public SubmitLoanResponse submitLoan(SubmitLoanRequest request) {
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        Bid bid = new Bid();
        Long bidId = IdentifierGenerator.nextId();
        bid.setId(bidId);
        bid.setUserId(userId);
        bid.setBidNo(String.valueOf(IdentifierGenerator.nextId()));
        bid.setTitle("消费贷");
        bid.setProductCode("1");
        bid.setApplyAmount(new BigDecimal(request.getApplyAmount()));
        bid.setPeriod(Integer.valueOf(request.getPeriod()));
        bid.setInBank(request.getInBank());
        bid.setInAccount(request.getInAccount());
        bid.setPurposeCode(request.getPurposeId());
        bid.setClient((byte)1);
        bid.setStatus(BidStatusEnum.RISK_ING.getCode().byteValue());

        UserBank userBank = new UserBank();
        userBank.setId(Long.valueOf(IdentifierGenerator.nextId()));
        userBank.setInBank(request.getInBank());
        userBank.setInAccount(request.getInAccount());
        userBank.setCreateTime(LocalDateTime.now());
        bidRepository.save(bid,userBank);
        return SubmitLoanResponse.builder().bid(String.valueOf(bidId)).build();

    }
}