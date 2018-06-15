package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 广告产品返回参数
 *
 * @author wuchengwu
 * @date 2018/6/15
 */
@Data
public class AdsProductResponse {
    private String title;
    private String imgUrl;
    private String linkUrl;
    private String amountInterval;
    private Integer weights;
    private String upTime;
    private String downTime;
}