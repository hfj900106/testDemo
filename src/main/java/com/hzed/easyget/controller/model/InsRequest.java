package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * ins认证风控回调请求参数
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class InsRequest {
    /**
     * 用户id
     */
    @NotNull(message = "{param.auth.userId.isNotEmpty}")
    private Long userId ;

    /**
     * 认证结果"ok"认证成功
     */
    @NotBlank(message = "{param.auth.resultCode.isNotEmpty}")
    private String resultCode ;

}
