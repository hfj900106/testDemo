package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoanDetailRequest;
import com.hzed.easyget.controller.model.LoanDetailResponse;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.persistence.auto.entity.Bid;
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

    public LoanDetailResponse loanDetail(LoanDetailRequest request) {
        LoanDetailResponse loanDetailResponse = new LoanDetailResponse();
        String bidStr = request.getBid();
        Long bidId = Long.valueOf(bidStr);
        Bid bid = bidRepository.findByIdWithExp(bidId);
        loanDetailResponse.setApplyAmount(bid.getApplyAmount().toString());
        loanDetailResponse.setApplyTime(DateUtil.dateToStr(bid.getCreateTime()));
        loanDetailResponse.setInBank(bid.getInBank());
        loanDetailResponse.setInAccount(bid.getInAccount());
        LocalDateTime auditTime = DateUtil.addDaysBymin(bid.getCreateTime(), systemProp.getExpectedAuditTimeInterval().intValue());
        loanDetailResponse.setAuditTime(DateUtil.dateToStr(auditTime));
        loanDetailResponse.setLoanTime(DateUtil.dateToStr(DateUtil.addDaysBymin(auditTime,systemProp.getExpectedLendingTimeInterval().intValue())));
        return loanDetailResponse;
    }
}