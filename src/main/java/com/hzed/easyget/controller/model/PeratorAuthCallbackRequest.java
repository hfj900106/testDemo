package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 运营商认证风控回调请求参数
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class PeratorAuthCallbackRequest {

    @NotNull(message = "[userId]不能为空")
    private Long userId ;
    @NotBlank(message = "[resultCode]不能为空，ok为成功")
    private String resultCode ;

}
