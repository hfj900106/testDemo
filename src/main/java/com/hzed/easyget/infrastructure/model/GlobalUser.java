package com.hzed.easyget.infrastructure.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户登录信息，存放在token中，有需要可以新增
 *
 * @author guichang
 */
@Data
public class GlobalUser {
    @NotNull(message = "[userId]不可为空")
    private Long userId;
    @NotBlank(message = "[mobile]不可为空")
    private String mobile;
}
