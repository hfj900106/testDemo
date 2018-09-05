package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Length(min = 1,max = 128, message = "[companyName]不能为空，长度在1-128之间")
    private String companyName;
    @Length(min = 1,max = 256, message = "[companyAddr]不能为空，长度在1-256之间")
    private String companyAddr;
    @Length(min = 1,max = 256, message = "[companyAddrDetail]不能为空，长度在1-256之间")
    private String companyAddrDetail;
    @Length(min = 1,max = 64,message = "[email]不能为空，长度在1-64之间")
    private String email;
    @NotBlank(message = "[relationship1]不能为空")
    private String relationship1;
    @NotBlank(message = "[personName1]不能为空")
    private String personName1;
    @NotBlank(message = "[personTel1]不能为空")
    private String personTel1;
    @NotBlank(message = "[relationship2]不能为空")
    private String relationship2;
    @NotBlank(message = "[personName2]不能为空")
    private String personName2;
    @NotBlank(message = "[personTel2]不能为空")
    private String personTel2;
    @NotBlank(message = "[companyTel]不能为空")
    private String companyTel;
    @NotBlank(message = "[childrenNumber]不能为空")
    private String childrenNumber;
    @NotBlank(message = "[maritalStatus]不能为空")
    private String maritalStatus;
    @NotBlank(message = "[birthMotherName]不能为空")
    private String birthMotherName;
    @NotBlank(message = "[jobType]工作类型code不能为空")
    private String jobType ;
    @NotBlank(message = "[industry]行业code不能为空")
    private String industry ;
    @NotBlank(message = "[payday]不能为空")
    private String payday;
    @NotBlank(message = "[monthlyIncome]月收入code不能为空")
    private String monthlyIncome ;
    @NotBlank(message = "[picSuffix]不能为空")
    private String picSuffix;
    @NotNull(message = "[picTypeAndPathBase64Str]不能为空，list元素为图片类型code拼接base64字符串")
    private List<String> picTypeAndPathBase64Str;

}