package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@Data
public class UserRequest {
    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[idCard]不能为空")
    private String idCard;
}