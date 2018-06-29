package com.hzed.easyget.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description：bluepay返回实体
 * @author：[zhangruilin]
 * @time：2018/6/20-10:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class PayResponse {
    private String code;

    private String msg;

    private boolean flag;

    private String data;
}
