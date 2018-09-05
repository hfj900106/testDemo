package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 地区字典表请求参数
 *
 * @author wuchengwu
 * @date 2018/6/20
 */
@Data
public class IDAreaRequest {

    @NotBlank(message = "[parent]不能为空")
    private String parent;
}