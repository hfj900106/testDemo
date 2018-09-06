package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * facebook和ins认证时数据推风控请求参数
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class FacebookInsRequest {
    @NotBlank(message = "[taskId]不能为空")
    private String taskId ;
    @NotBlank(message = "[facebookOrIns]不能为空，facebook|ins")
    private String facebookOrIns ;

}
