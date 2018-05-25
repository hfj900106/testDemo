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

    @NotBlank(message = "[realName]不能为空")
    private String realName;
    @NotBlank(message = "[idCardNo]不能为空")
    private String idCardNo;
    @NotNull(message = "[gender]不能为空")
    private Integer gender;
    @NotBlank(message = "[idCardPhotoPath]不能为空")
    private String idCardPhotoPath;
    @NotBlank(message = "[facePhotoPath]不能为空")
    private String facePhotoPath;
}