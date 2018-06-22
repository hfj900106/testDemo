package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 广告列表请求参数
 *
 * @author wuchengwu
 * @date 2018/6/22
 */
@Data
public class AdsProductListRequest {
    private Integer pageNo = 0;
    private Integer pageSize = 5;
}