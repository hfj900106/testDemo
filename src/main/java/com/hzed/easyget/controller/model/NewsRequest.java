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
    @Min(0L) @NotNull(message = "[pageNo]不能为空，最小值0")
    private Integer pageNo;
    @Min(1L) @NotNull(message = "[pageSize]不能为空，最小值1")
    private Integer pageSize;

}