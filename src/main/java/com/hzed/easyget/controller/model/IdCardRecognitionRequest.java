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

    @NotBlank(message = "[idCardBase64ImgStr]不能为空")
    private String idCardBase64ImgStr;
    @NotBlank(message = "[bizToken]不能为空")
    private String bizToken;
    @NotNull(message = "[ocrData]不能为空")
    private byte[] ocrData;
}