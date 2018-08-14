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
    @Length(min = 1,max = 128, message = "{param.auth.companyName.extraLong}")
    private String companyName;
    @Length(min = 1,max = 256, message = "{param.auth.companyAddr.extraLong}")
    private String companyAddr;
    @Length(min = 1,max = 256, message = "{param.auth.companyAddrDetail.extraLong}")
    private String companyAddrDetail;
    @Length(min = 1,max = 64,message = "{param.auth.email.extraLong}")
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