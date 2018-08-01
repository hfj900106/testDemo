package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * @author guichang
 * @date 2018/7/23
 */

@Data
public class DictUpdateRequest {
    private String name;
    private String code;
    private String value;
    private String label;
    private String language;
    private String remark;
}