package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Facebook认证风控回调请求参数
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class FacebookRequest {

    @NotNull(message = "[userId]不能为空")
    private Long userId ;
    @NotBlank(message = "[resultCode]不能为空")
    private String resultCode ;

}
