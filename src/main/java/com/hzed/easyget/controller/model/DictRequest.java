package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 字典表请求参数
 *
 * @author wuchengwu
 * @date 2018/6/19
 */
@Data
public class DictRequest {
    @NotBlank(message = "[moduleCode]不能为空")
    private String moduleCode;
}