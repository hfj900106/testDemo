package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 弹窗返回参数
 *
 * @author wuchengwu
 * @data 2018/5/25
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {

    private String newsTitle;
    private String imgUrl;
    private String toUrl;
}