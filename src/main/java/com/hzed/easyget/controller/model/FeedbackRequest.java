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
    @NotBlank(message = "{param.auth.questionType.isNotEmpty}")
    private String questionType;
    @NotBlank(message = "{param.auth.questionDesc.isNotEmpty}")
    private String questionDesc;
    @NotNull(message = "{param.auth.images.isNotEmpty}")
    private List<String> images;
    @NotBlank(message = "{param.auth.picSuffix.isNotEmpty}")
    private String picSuffix;
}