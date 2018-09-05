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
    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
    @NotNull(message = "[loanAmount]不能为空，不通过设为0")
    private BigDecimal loanAmount;
    @NotBlank(message = "[resultCode]不能为空，4-通过 3-失败 2-人审 8-撤销")
    private String resultCode;
    @NotNull(message = "[handleTime]不能为空")
    private Long handleTime;
    private String message;
    /** 人审标识 */
    private Integer manual;
    private Long updateBy;

}
