package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 版本更新请求参数
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@Data
public class AppVersionRequest {
    @NotBlank(message = "[channel]不能为空")
    private String channel;
    @NotNull(message = "[versionCode]不能为空")
    private Integer versionCode;
}