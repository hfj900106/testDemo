package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 通讯录验证请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class ContactsRequest {
    private String contacts;
    private String callLogs;
    @NotNull(message = "{param.auth.timeStamp.isNotEmpty}")
    private Long timeStamp;
}