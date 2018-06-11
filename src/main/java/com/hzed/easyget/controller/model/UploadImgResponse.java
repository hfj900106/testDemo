package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传返回参数
 *
 * @author wuchengwu
 * @data 2018/6/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadImgResponse {
    private String visitUrl;
}