package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 公告列表请求参数
 *
 * @author wuchengwu
 * @date 2018/6/22
 */
@Data
public class NewsRequest {
    @Min(0L) @NotNull(message = "{param.home.pageNo.isNotEmpty}")
    private Integer pageNo;
    @Min(1L) @NotNull(message = "{param.home.pageSize.isNotEmpty}")
    private Integer pageSize;

}