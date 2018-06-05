package com.hzed.easyget.controller.model;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "[jobType]不能为空")
    private Integer jobType ;
    /**
     * 月收入
     */
    @NotNull(message = "[monthlyIncome]不能为空")
    private Integer monthlyIncome ;
    /**
     * 工作证保存路径
     */
    @NotBlank(message = "[employeeCard]不能为空")
    private String employeeCard ;
    /**
     * 工作场所照片保存路径
     */
    @NotBlank(message = "[employeeCard]不能为空")
    private String workplace ;
}
