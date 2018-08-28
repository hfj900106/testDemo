package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 个人信息认证
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class PersonInfoAuthRequest {

    @NotBlank(message = "{param.auth.education.isNotEmpty}")
    private String education;
    @Length(min = 1,max = 128, message = "{param.auth.companyName.extraLong}")
    private String companyName;
    @Length(min = 1,max = 256, message = "{param.auth.companyAddr.extraLong}")
    private String companyAddr;
    @Length(min = 1,max = 256, message = "{param.auth.companyAddrDetail.extraLong}")
    private String companyAddrDetail;
    @Length(min = 1,max = 64,message = "{param.auth.email.extraLong}")
    private String email;
    @NotBlank(message = "{param.auth.relationship1.isNotEmpty}")
    private String relationship1;
    @NotBlank(message = "{param.auth.personName1.isNotEmpty}")
    private String personName1;
    @NotBlank(message = "{param.auth.personTel1.isNotEmpty}")
    private String personTel1;
    @NotBlank(message = "{param.auth.relationship2.isNotEmpty}")
    private String relationship2;
    @NotBlank(message = "{param.auth.personName2.isNotEmpty}")
    private String personName2;
    @NotBlank(message = "{param.auth.personTel2.isNotEmpty}")
    private String personTel2;

    @NotBlank(message = "{param.auth.companyTel.isNotEmpty}")
    private String companyTel;
    @NotNull(message = "{param.auth.childrenNumber.isNotEmpty}")
    private Integer childrenNumber;
    @NotBlank(message = "{param.auth.maritalStatus.isNotEmpty}")
    private String maritalStatus;
    @NotBlank(message = "{param.auth.birthMotherName.isNotEmpty}")
    private String birthMotherName;

}