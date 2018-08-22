package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 交易记录
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class ProfessionalRequest {
    /**
     * 工作类型
     */
    @NotBlank(message = "{param.auth.jobType.isNotEmpty}")
    private String jobType ;
    /**
     * 行业
     */
    @NotBlank(message = "{param.auth.industry.isNotEmpty}")
    private String industry ;
    /**
     * 发薪日
     */
    @NotBlank(message = "{param.auth.payday.isNotEmpty}")
    private String payday;
    /**
     * 月收入
     */
    @NotBlank(message = "{param.auth.monthlyIncome.isNotEmpty}")
    private String monthlyIncome ;

    /**
     * 图片后缀
     */
    @NotBlank(message = "{param.auth.picSuffix.isNotEmpty}")
    private String picSuffix;

    /**
     * 图片类型拼接base64字符串
     */
    @NotBlank(message = "{param.auth.picPathBase64Str.isNotEmpty}")
    private String picTypeAndPathBase64Str;
}