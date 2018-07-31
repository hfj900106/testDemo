package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 弹窗返回参数
 *
 * @author wuchengwu
 * @date 2018/5/25
 */
@Data
public class NewsResponse {

    private String title;
    private String content;
    private String toUrl;
    private Long createTime;
    private Long id;
}