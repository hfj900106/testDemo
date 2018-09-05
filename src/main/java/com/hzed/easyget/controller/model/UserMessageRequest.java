package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公告信息请求参数
 *
 * @author wuchengwu
 * @date 2018/7/14
 */
@Data
public class UserMessageRequest {
    @NotBlank(message = "[title]不能为空")
    private String title;
    @NotBlank(message = "[appMessage]不能为空")
    private String appMessage;
    @NotBlank(message = "[message]不能为空")
    private String message;
}