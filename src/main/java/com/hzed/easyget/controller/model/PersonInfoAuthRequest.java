package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "{param.auth.companyName.isNotEmpty}")
    private String companyName;
    @NotBlank(message = "{param.auth.companyAddr.isNotEmpty}")
    private String companyAddr;
    @NotBlank(message = "{param.auth.companyAddrDetail.isNotEmpty}")
    private String companyAddrDetail;
    @NotBlank(message = "{param.auth.email.isNotEmpty}")
    @Length(max = 64,message = "{param.auth.email.extraLong}")
    private String email;
    @NotBlank(message = "{param.auth.parentName.isNotEmpty}")
    private String parentName;
    @NotBlank(message = "{param.auth.parentTel.isNotEmpty}")
    private String parentTel;
    @NotBlank(message = "{param.auth.relationship.isNotEmpty}")
    private String relationship;
    @NotBlank(message = "{param.auth.relatedPersonName.isNotEmpty}")
    private String relatedPersonName;
    @NotBlank(message = "{param.auth.relatedPersonTel.isNotEmpty}")
    private String relatedPersonTel;

}