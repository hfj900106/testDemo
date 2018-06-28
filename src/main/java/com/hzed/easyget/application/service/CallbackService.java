package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackResponse;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.BidProgress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
@Service
public class CallbackService {

    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private BidRepository bidRepository;

    public PushBidCallbackResponse pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        BigDecimal loanAmount = request.getLoanAmount();
        byte status = "1".equals(request.getResultCode()) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.AUDIT_FAIL.getCode().byteValue();

        LocalDateTime dateTime = DateUtil.timestampToLocalDateTimeTo(Long.parseLong(request.getHandleTime()));
        Bid bid = bidRepository.findById(bidId);
        if(ObjectUtils.isEmpty(bid)){
            log.error("回调处理的标ID：{}不存在标的表中",bidId);
        }else {
            AbstractProduct absProduct = ProductFactory.getProduct(ProductEnum.EasyGet).createProduct(loanAmount, bid.getPeriod());
            tempTableRepository.pushBidCallback(
                    Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(dateTime).auditFee(absProduct.getHeadFee()).status(status).build(),
                    BidProgress.builder().id(IdentifierGenerator.nextId()).bidId(bidId).type(BidProgressTypeEnum.AUDIT.getCode().byteValue()).build(),
                    bidId);
        }

        //成功或者失败都返回
        return PushBidCallbackResponse.getSuccessResponse();
    }

}
