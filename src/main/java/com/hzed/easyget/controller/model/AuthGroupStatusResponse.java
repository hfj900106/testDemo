package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 认证状态返回值
 *
 * @author wuchengwu
 * @date 2018/5/23
 */
@Data
public class AuthGroupStatusResponse {
    private String authCode;
    private String authStatus;
    private String authName;
    private String authGroup;
}