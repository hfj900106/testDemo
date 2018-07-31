package com.hzed.easyget.controller.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 消息公告列表
 *
 * @author wuchengwu
 * @since 2018/7/31
 */
@Data
public class NewsAndMessageResponse {

    private String title;
    private boolean hasRead;
    private String toUrl;
    private Long userId;
    private LocalDateTime createTime;
    private Long id;


}