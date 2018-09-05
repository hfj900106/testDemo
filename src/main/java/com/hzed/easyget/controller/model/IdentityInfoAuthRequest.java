package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
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

    @Length(min = 1, max = 32, message = "[realName]长度在1-32之间")
    private String realName;
    @NotBlank(message = "[idCardNo]不能为空")
    private String idCardNo;
    @NotNull(message = "[gender]不能为空")
    private Integer gender;
    @NotBlank(message = "[idCardBase64ImgStr]不能为空")
    private String idCardBase64ImgStr;
    @NotBlank(message = "[faceBase64ImgStr]不能为空")
    private String faceBase64ImgStr;
    @NotBlank(message = "[picSuffix]不能为空")
    private String picSuffix;
    @NotNull(message = "[religion]不能为空")
    private String religion;
}