package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 个人信息认证
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class PersonInfoAuthRequest {

    @NotBlank(message = "[education]不能为空")
    private String education;
    @NotBlank(message = "[companyName]不能为空")
    private String companyName;
    @NotBlank(message = "[companyAddr]不能为空")
    private String companyAddr;
    @NotBlank(message = "[companyAddrDetail]不能为空")
    private String companyAddrDetail;
    @NotBlank(message = "[email]不能为空")
    private String email;
    @NotBlank(message = "[parentName]不能为空")
    private String parentName;
    @NotBlank(message = "[parentTel]不能为空")
    private String parentTel;
    @NotBlank(message = "[relationship]不能为空")
    private String relationship;
    @NotBlank(message = "[relatedPersonName]不能为空")
    private String relatedPersonName;
    @NotBlank(message = "[relatedPersonTel]不能为空")
    private String relatedPersonTel;

}