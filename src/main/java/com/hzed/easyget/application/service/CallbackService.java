package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.NxCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackResponse;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
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
    @Autowired
    private AsyncService asyncService;

    public PushBidCallbackResponse pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        Bid bid = bidRepository.findById(bidId);
        if (ObjectUtils.isEmpty(bid)) {
            log.error("回调处理标ID：{} 不存在标的表中", bidId);
            return PushBidCallbackResponse.getSuccessResponse();
        }
        byte bidStatus = bid.getStatus();
        // 待机审核待人审的都可以处理
        if (bidStatus == BidStatusEnum.RISK_ING.getCode().byteValue() || bidStatus == BidStatusEnum.MANMADE_ING.getCode().byteValue()) {
        } else {
            log.error("回调处理标ID：{} 状态为：{}，不处理", bidId, bidStatus);
            return PushBidCallbackResponse.getSuccessResponse();
        }
        // 人审标志
        Integer manual = request.getManual();
        BigDecimal loanAmount = request.getLoanAmount();
        byte status = (byte) 0;
        String remark;
        // 人审
        if (!ObjectUtils.isEmpty(manual) && manual == 1) {
            remark = "人审";
        } else {
            remark = "机审";
        }

        String code = request.getResultCode();
        //通过
        if (ComConsts.RISK_4.equals(code)) {
            status = BidStatusEnum.AUDIT_PASS.getCode().byteValue();
            log.info(remark + "-审核通过，标id：{}，金额：{}", bidId, loanAmount);
            // 发送短信
            asyncService.sendSmsOfPushResult(bidId, true);
        }
        // 失败
        else if (ComConsts.RISK_3.equals(code)) {
            status = BidStatusEnum.AUDIT_FAIL.getCode().byteValue();
            log.info(remark + "-审核不通过，标id：{}，原因：{}", bidId, request.getMessage());
            // 发送短信
            asyncService.sendSmsOfPushResult(bidId, false);
        }
        // 人审
        else if (ComConsts.RISK_2.equals(code)) {
            status = BidStatusEnum.MANMADE_ING.getCode().byteValue();
            log.info("标id：{} 进入人审，原因：{}", bidId, request.getMessage());
        }
        // 撤销
        else if (ComConsts.RISK_8.equals(code)) {
            status = BidStatusEnum.CANCEL.getCode().byteValue();
            log.info("标id：{} 撤销，原因：{}", bidId, request.getMessage());
        }

        LocalDateTime dateTime = DateUtil.timestampToLocalDateTimeTo(request.getHandleTime());

        AbstractProduct absProduct = ProductFactory.getProduct().createProduct(loanAmount, bid.getPeriod());
        tempTableRepository.pushBidCallback(
                Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(LocalDateTime.now()).auditFee(absProduct.getHeadFee()).status(status).updateBy(request.getUpdateBy()).remark(remark).build(),
                BidProgress.builder().id(System.nanoTime()).bidId(bidId).type(BidProgressTypeEnum.AUDIT.getCode().byteValue()).handleResult(request.getMessage())
                        .handleTime(dateTime).build(),
                bidId);

        // 成功或者失败都返回
        return PushBidCallbackResponse.getSuccessResponse();
    }

    /**
     * TODO 保存短信状态
     */
    public void nxCallback(NxCallbackRequest request) {

    }
}
