package com.hzed.easyget.infrastructure.exception;

import lombok.Data;

/**
 * 异常基本类
 *
 * @author guichang
 */

@Data
public class BaseBizException extends RuntimeException {

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误信息占位
     */
    private Object[] objs;
    /**
     * 返回response附加数据
     */
    private Object data;

    /**
     * 除了错误码本身描述的提示信息外，额外补充的信息
     */
    private String extraMsg;

    /**
     * 构造器
     */
    public BaseBizException() {
        super();
    }

    public BaseBizException(Throwable cause) {
        super(cause);
    }

    public BaseBizException(String code, String msg) {
        super(msg);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BaseBizException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BaseBizException(String code, String msg, String extraMsg) {
        this(code, msg + "-" + extraMsg);
        this.extraMsg = extraMsg;
    }

    public BaseBizException(String code, String msg, Object data, Object[] objs) {
        this(code, msg);
        this.data = data;
        this.objs = objs;
    }

    @Override
    public String getMessage() {
        return this.errorMsg;
    }
}
