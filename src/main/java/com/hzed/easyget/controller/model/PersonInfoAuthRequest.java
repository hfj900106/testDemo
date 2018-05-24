package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 个人信息认证
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class PersonInfoAuthRequest {

    @NotBlank(message = "[date]不能为空")
    private String date;

}