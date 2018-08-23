package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 牛信运营商回调请求参数
 * @author guichang
 * @data 2018/8/8
 */
@Data
public class NxCallbackRequest {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态代码：2:成功下发，其他数字都未下发成功
     */
    private String status;

    /**
     * 回执内容： DELIVRD \ UNDELIV （DELIVRD表示用户成功接收短信）
     */
    private String result;

    /**
     * 回执报告时间，格式为：yyyy-MM-dd HH:mm:ss
     */
    private String drtime;

    /**
     * 短信id，对应的短信提交成功得到的id
     */
    private String messageid;
}
