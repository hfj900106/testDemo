package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 人脸识别
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class FaceRecognitionRequest {

    @NotBlank(message = "{param.auth.faceBase64ImgStr.isNotEmpty}")
    private String faceBase64ImgStr;
    @NotNull(message = "{param.auth.timeStamp.isNotEmpty}")
    private Long timeStamp;

}