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

    @NotBlank(message = "教育情况不能为空")
    private String education;
    @NotBlank(message = "公司名称不能为空")
    private String companyName;
    @NotBlank(message = "公司地址不能为空")
    private String companyAddr;
    @NotBlank(message = "公司详细地址不能为空")
    private String companyAddrDetail;
    @NotBlank(message = "邮件不能为空")
    private String email;
    @NotBlank(message = "父母名称不能为空")
    private String parentName;
    @NotBlank(message = "父母电话不能为空")
    private String parentTel;
    @NotBlank(message = "亲朋关系不能为空")
    private String relationship;
    @NotBlank(message = "亲朋姓名不能为空")
    private String relatedPersonName;
    @NotBlank(message = "亲朋电话不能为空")
    private String relatedPersonTel;

}