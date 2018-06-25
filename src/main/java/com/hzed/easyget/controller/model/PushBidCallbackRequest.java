package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 推送资产-审核回调请求参数
 * @author hfj
 * @data 2018/6/9
 */
@Data
public class PushBidCallbackRequest {
    @NotNull(message = "{param.callback.bidId.isNotEmpty}")
    private Long bidId;
    /** 审核金额，不通过设为0 */
    @NotNull(message = "{param.callback.loanAmount.isNotEmpty}")
    private BigDecimal loanAmount;
    /** 1-标识通过 0-不通过 */
    @NotBlank(message = "{param.callback.resultCode.isNotEmpty}")
    private String resultCode;
    @NotBlank(message = "{param.callback.handleTime.isNotEmpty}")
    private String handleTime;
}
