package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 暂无描述
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@Data
public class AppVersionRequest {
    @NotBlank(message = "版本号不能为空")
    private String oldVersion;
    @NotBlank(message = "渠道不能为空")
    private String channel;
}