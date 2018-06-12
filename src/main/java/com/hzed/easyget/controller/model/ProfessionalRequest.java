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
    @NotNull(message = "工作类型不能为空")
    private Integer jobType ;
    /**
     * 月收入
     */
    @NotNull(message = "月收入不能为空")
    private Integer monthlyIncome ;
    /**
     * 工作证
     */
    @NotBlank(message = "工作证不能为空")
    private String employeeCardBase64ImgStr ;
    /**
     * 工作场所照片
     */
    @NotBlank(message = "工作场所照片不能为空")
    private String workplaceBase64ImgStr ;

    @NotBlank(message = "图片后缀不能为空")
    private String picSuffix;
}
