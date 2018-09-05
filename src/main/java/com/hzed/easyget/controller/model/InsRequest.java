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

    @NotNull(message = "[userId]不能为空")
    private Long userId ;
    @NotBlank(message = "[resultCode]不能为空，ok 认证成功")
    private String resultCode ;

}
