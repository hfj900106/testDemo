package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新token返回参数
 *
 * @author wuchengwu
 * @data 2018/5/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTokenResponse {
    private String token;
}