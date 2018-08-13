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
    /** 4-通过    3-失败   2-人审 8-撤销 */
    @NotBlank(message = "{param.callback.resultCode.isNotEmpty}")
    private String resultCode;
    @NotNull(message = "{param.callback.handleTime.isNotEmpty}")
    private Long handleTime;

    private String message;
    /** 人审标识 */
    private Integer manual;

    private Long updateBy;

}
