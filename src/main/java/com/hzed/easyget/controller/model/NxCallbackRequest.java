package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 牛信运营商回调请求参数
 * @author guichang
 * @data 2018/8/8
 */
@Data
public class NxCallbackRequest {
    private String phone;
    private String status;
    private String result;
    private String drtime;
    private String messageid;
}
