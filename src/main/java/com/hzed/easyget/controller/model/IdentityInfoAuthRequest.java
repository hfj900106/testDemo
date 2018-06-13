package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 身份信息认证
 *
 * @author hfj
 * @date 2018/5/24
 */

@Data
public class IdentityInfoAuthRequest {

    @NotBlank(message = "{param.auth.realName.isNotEmpty}")
    private String realName;
    @NotBlank(message = "{param.auth.idCardNo.isNotEmpty}")
    private String idCardNo;
    @NotNull(message = "{param.auth.gender.isNotEmpty}")
    private Integer gender;
    @NotBlank(message = "{param.auth.idCardBase64ImgStr.isNotEmpty}")
    private String idCardBase64ImgStr;
    @NotBlank(message = "{param.auth.faceBase64ImgStr.isNotEmpty}")
    private String faceBase64ImgStr;
    @NotBlank(message = "{param.auth.picSuffix.isNotEmpty}")
    private String picSuffix;
}