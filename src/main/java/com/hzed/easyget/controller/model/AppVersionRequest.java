package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 版本更新请求参数
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@Data
public class AppVersionRequest {
    @NotNull(message = "{param.home.oldVersion.isNotEmpty}")
    private Integer oldVersion;
}