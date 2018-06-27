package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 版本更新请求参数
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@Data
public class AppVersionRequest {
    @NotBlank(message = "{param.home.oldVersion.isNotEmpty}")
    private String oldVersion;
}