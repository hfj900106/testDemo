package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通讯录验证请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class ContactsRequest {

    @NotBlank(message = "[contacts]不能为空")
    private String contacts;

}