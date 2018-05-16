package com.hzed.easyget.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录信息，存放在token中，有需要可以新增
 * @author guichang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private Long userId;
    private String mobile;
}
