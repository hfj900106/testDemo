package com.hzed.easyget.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 请求风控返回参数
 *
 * @author wuchengwu
 * @date 2018/6/14
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskResponse {

    private RiskHead head;
    private Object body;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class RiskHead {
        private String ver;
        private String status;
        private String error_code;
        private String error_msg;
    }

}