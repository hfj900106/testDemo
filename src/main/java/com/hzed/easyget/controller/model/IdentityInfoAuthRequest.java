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

    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    @NotBlank(message = "身份证号不能为空")
    private String idCardNo;
    @NotNull(message = "性别不能为空")
    private Integer gender;
    @NotBlank(message = "身份证图片不能为空")
    private String idCardBase64ImgStr;
    @NotBlank(message = "人脸图片不能为空")
    private String faceBase64ImgStr;
    @NotBlank(message = "图片后缀不能为空")
    private String picSuffix;
    @NotNull(message = "时间戳不能为空")
    private Long timeStamp;
}