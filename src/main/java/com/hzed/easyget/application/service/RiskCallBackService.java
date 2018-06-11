package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.controller.model.PushBidCallBackRequest;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.BidProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Service
public class RiskCallBackService {

    @Autowired
    private TempTableRepository tempTableRepository;

    public void pushBidCallBack(PushBidCallBackRequest request) {
        Long bidId = request.getBidId();
        BigDecimal loanAmount = request.getLoanAmount();
        String resultCode = request.getResultCode();
        LocalDateTime dateTime = request.getHandleTime();
        tempTableRepository.pushBidCallBack(
                Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(dateTime)
                        .status("0000".equals(resultCode) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.AUDIT_FAIL.getCode().byteValue()).build(),
                BidProgress.builder().bidId(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.AUDIT.getCode().byteValue())
                        .createTime(dateTime).build(),
                bidId);
    }

}
