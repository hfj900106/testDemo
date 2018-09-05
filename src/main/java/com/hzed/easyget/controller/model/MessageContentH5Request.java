package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * h5获取消息内容请求参数
 *
 * @author wuchengwu
 * @since 2018/7/12
 */
@Data
public class MessageContentH5Request {
    @NotNull(message = "[id]不能为空")
    private Long id;
    @NotNull(message = "[type]不能为空")
    private String type;
}