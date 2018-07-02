package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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
     * 月收入
     */
    @NotBlank(message = "{param.auth.monthlyIncome.isNotEmpty}")
    private String monthlyIncome ;
    /**
     * 工作证
     */
    @NotBlank(message = "{param.auth.employeeCardBase64ImgStr.isNotEmpty}")
    private String employeeCardBase64ImgStr ;
    /**
     * 工作场所照片
     */
    @NotBlank(message = "{param.auth.workplaceBase64ImgStr.isNotEmpty}")
    private String workplaceBase64ImgStr ;

    @NotBlank(message = "{param.auth.picSuffix.isNotEmpty}")
    private String picSuffix;
}
