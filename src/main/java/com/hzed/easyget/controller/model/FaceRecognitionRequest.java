package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 人脸识别
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class FaceRecognitionRequest {

    @NotBlank(message = "[faceBase64ImgStr]不能为空")
    private String faceBase64ImgStr;
}