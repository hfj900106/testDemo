package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 消息公告列表返回参数
 *
 * @author wuchengwu
 * @since 2018/7/11
 */
@Data
public class MessageListResponse {
    private String title;
    private String message;
    private boolean read;
    private String toUrl;
    private Long createTime;


}