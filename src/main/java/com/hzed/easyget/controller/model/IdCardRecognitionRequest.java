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

    @NotBlank(message = "{param.auth.idCardBase64ImgStr.isNotEmpty}")
    private String idCardBase64ImgStr;
    @NotBlank(message = "{param.auth.bizToken.isNotEmpty}")
    private String bizToken;
    @NotNull(message = "{param.auth.ocrData.isNotEmpty}")
    private byte[] ocrData;
}