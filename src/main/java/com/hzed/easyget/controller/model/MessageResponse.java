package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 弹窗返回参数
 *
 * @author wuchengwu
 * @date 2018/5/25
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    private String messageTitle;
    private String message;
    private String toUrl;
    private Long createTime;
    private Long id;
}