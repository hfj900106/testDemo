package com.hzed.easyget.infrastructure.model;

import lombok.Data;

/**
 * 用户登录信息，存放在token中，有需要可以新增
 *
 * @author guichang
 */
@Data
public class GlobalUser {
    private Long userId;
    private String mobile;
}
