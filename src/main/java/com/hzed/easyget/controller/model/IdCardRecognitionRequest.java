package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 身份证识别
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class IdCardRecognitionRequest {

    @NotBlank(message = "身份证图片不能为空")
    private String idCardBase64ImgStr;
    @NotNull(message = "时间戳不能为空")
    private Long timeStamp;

}