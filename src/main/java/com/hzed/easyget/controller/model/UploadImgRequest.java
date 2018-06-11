package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传图片请求参数
 *
 * @author wuchengwu
 * @data 2018/6/11
 */
@Data
public class UploadImgRequest {
    @NotBlank(message = "图片不能为空")
    private String imgBase64;
    @NotBlank(message = "图片后缀不能为空")
    private String pictureSuffix;
}