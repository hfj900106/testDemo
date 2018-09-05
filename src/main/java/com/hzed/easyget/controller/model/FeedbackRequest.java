package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 问题反馈请求
 *
 * @author hfj
 * @date 2018/08/30
 */

@Data
public class FeedbackRequest {
    @NotBlank(message = "[questionType]不能为空")
    private String questionType;
    @NotBlank(message = "[questionDesc]不能为空")
    private String questionDesc;
    @NotNull(message = "[images]不能为空")
    private List<String> images;
    @NotBlank(message = "[picSuffix]不能为空")
    private String picSuffix;
}