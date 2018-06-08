package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author guichang
 * @date 2018/6/8
 */

@Getter
public enum JobStatusEnum {
    /** 过规则 */
    WAIT(1,"待处理"),
    FALI(2,"处理失败"),
    SUCCESS(3,"处理成功");

    private Integer code;
    private String msg;

    JobStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


}
