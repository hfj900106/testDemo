package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 认证状态返回值
 *
 * @author wuchengwu
 * @date 2018/5/23
 */
@Data
public class AuthStatusResponse {
    private String authIdentity;
    private String authAddress;
    private String authSMS;
    private String authPersonalInfo;
    private String authMobileOperator;
    private String authInformation;
    private String authFacebook;
    private String authIns;
}