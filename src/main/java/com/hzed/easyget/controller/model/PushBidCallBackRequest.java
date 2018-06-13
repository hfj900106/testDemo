package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 推送资产-审核回调请求参数
 * @author hfj
 * @data 2018/6/9
 */
@Data
public class PushBidCallBackRequest {
    @NotNull(message = "{param.riskCallBack.bidId.isNotEmpty}")
    private Long bidId;
    @NotNull(message = "{param.riskCallBack.loanAmount.isNotEmpty}")
    private BigDecimal loanAmount;
    @NotBlank(message = "{param.riskCallBack.resultCode.isNotEmpty}")
    private String resultCode;
    @NotBlank(message = "{param.riskCallBack.handleTime.isNotEmpty}")
    private LocalDateTime handleTime;
}
