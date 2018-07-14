package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 弹窗返回参数
 *
 * @author wuchengwu
 * @date 2018/5/25
 */
@Data
public class MessageResponse {

    private String messageTitle;
    private String appMessage;
    private String H5Message;
    private String toUrl;
    private Long createTime;
    private Long id;
    private boolean hasRead;
    private Long userId;
}