package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class RegisterH5FbRequest {
    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[fbH5Flag]不能为空")
    private String fbH5Flag;
    private String fromCode;

}