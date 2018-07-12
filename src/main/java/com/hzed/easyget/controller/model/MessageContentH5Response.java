package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * H5获取消息内容返回参数
 *
 * @author wuchengwu
 * @date 2018/7/12
 */
@Data
public class MessageContentH5Response {
    private String title;
    private String message;
    private String createTime;
}