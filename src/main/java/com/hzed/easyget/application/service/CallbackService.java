package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Service
public class CallbackService {

    @Autowired
    private TempTableRepository tempTableRepository;

    public void pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        BigDecimal loanAmount = request.getLoanAmount();
        String resultCode = request.getResultCode();
        LocalDateTime dateTime = request.getHandleTime();
        tempTableRepository.pushBidCallback(
                Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(dateTime)
                        .status("1".equals(resultCode) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.AUDIT_FAIL.getCode().byteValue()).build(),
                BidProgress.builder().bidId(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.AUDIT.getCode().byteValue())
                        .createTime(dateTime).build(),
                bidId);
    }

}
