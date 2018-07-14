package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 公告信息请求参数
 *
 * @author wuchengwu
 * @date 2018/7/14
 */
@Data
public class UserMessageRequest {
    private String title;
    private String appMessage;
    private String H5Message;
}