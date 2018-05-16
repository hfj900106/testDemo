package com.hzed.easyget.infrastructure.exception;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class BaseBizException extends RuntimeException {

    String errorCode;

    String errorMsg;

    /**
     * 除了错误码本身描述的提示信息外，额外补充的信息
     */
    private String extraMsg;

    /**
     * 错误信息模板
     */
    private final static String MSG_TEMPLATE = "错误码:{0}, 描述:{1}, 异常信息:{2}";


    /**
     * 构造器
     */
    public BaseBizException() {
        super();
    }

    /**
     * 构造器，含错误明细码枚举
     *
     */
    public BaseBizException(String code,String msg) {
        super(msg);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    /**
     * 构造器，含错误明细码枚举、异常
     *
     * @param cause    异常
     */
    public BaseBizException(String code,String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BaseBizException(String code,String msg, Throwable cause, String extraMsg) {
        super(msg + "-" + extraMsg, cause);
        this.errorCode = code;
        this.errorMsg = msg;
        this.extraMsg = extraMsg;
    }

    public BaseBizException(String code,String msg,  String extraMsg) {
        super(msg + "-" + extraMsg);
        this.errorCode = code;
        this.errorMsg = msg;
        this.extraMsg = extraMsg;
    }

    public BaseBizException(Throwable cause) {
        super(cause);
    }

    /**
     * 组装错误信息
     *
     * @return 错误信息详细描述
     */
    @Override
    public String getMessage() {
        return this.extraMsg == null ? this.errorMsg : this.errorMsg + "-" + this.extraMsg;
    }

    public String getSerializeMsg() {
        if (StringUtils.isNotEmpty(getExtraMsg())) {
            return getErrorMsg() + "-" + getExtraMsg();
        }
        return getErrorMsg();
    }
}
