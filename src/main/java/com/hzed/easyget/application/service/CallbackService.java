package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
@Service
public class CallbackService {

    @Autowired
    private TempTableRepository tempTableRepository;

    public PushBidCallbackResponse pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        //成功或者失败都返回
        PushBidCallbackResponse response = new PushBidCallbackResponse("0", "风控结果我已经收到了");
        try {
            log.info("风控审核回调，标的：{}", bidId);
            BigDecimal loanAmount = request.getLoanAmount();
            String resultCode = request.getResultCode();
            LocalDateTime dateTime = DateUtil.dateToLocalDateTime(new Date(Long.parseLong(request.getHandleTime())).toString());
            tempTableRepository.pushBidCallback(
                    Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(dateTime).auditFee(new EasyGetProduct(loanAmount).getHeadFee())
                            .status("1".equals(resultCode) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.AUDIT_FAIL.getCode().byteValue()).build(),
                    BidProgress.builder().id(IdentifierGenerator.nextId()).bidId(bidId).type(BidProgressTypeEnum.AUDIT.getCode().byteValue())
                            .createTime(dateTime).build(),
                    bidId);
        } catch (Exception ex) {
            return response;
        }
        return response;
    }

}
