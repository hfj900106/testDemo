package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 身份信息认证
 *
 * @author hfj
 * @date 2018/5/24
 */

@Data
public class IdentityInfoAuthRequest {

    @NotBlank(message = "[date]不能为空")
    private String date;

}