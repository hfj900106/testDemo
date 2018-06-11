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

/**
 * @author hfj
 * @date 2018/6/9
 */
@Service
public class RiskCallBackService {

    @Autowired
    private TempTableRepository tempTableRepository;

    public void pushBidCallBack(PushBidCallBackRequest request) {
        //TODO 审核结果 ，是不是审核不通过  将status设为人审？还是直接不通过？审核通过删除temp，不通过则更新temp？

        tempTableRepository.pushBidCallBack(
                Bid.builder().id(request.getBidId()).loanAmount(request.getLoanAmount()).updateTime(request.getDateTime())
                        .status("0000".equals(request.getResult()) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.MANMADE_ING.getCode().byteValue()).build(),
                BidProgress.builder().bidId(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.AUDIT.getCode().byteValue())
                        .createTime(request.getDateTime()).build(),
                request.getBidId());
    }
}
