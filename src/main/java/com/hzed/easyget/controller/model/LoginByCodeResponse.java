package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuchengwu
 * @since 2018/5/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginByCodeResponse {
    private String token;
    private String client;
    private Long userId;
    private Boolean isNew;
}