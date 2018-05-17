package com.hzed.easyget.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/3
 */

@Builder
@Data
public class UserResponse {

    private Long id;
    private Date time;
    private String name;
    private String idNumber;
    private String realityName;
}