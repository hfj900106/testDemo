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
    @NotNull(message = "标的ID不能为空")
    private Long bidId;
    @NotNull(message = "审核借款金额不能为空")
    private BigDecimal loanAmount;
    @NotBlank(message = "审核结果码不能为空")
    private String resultCode;
    @NotBlank(message = "处理时间不能为空")
    private LocalDateTime handleTime;
}
