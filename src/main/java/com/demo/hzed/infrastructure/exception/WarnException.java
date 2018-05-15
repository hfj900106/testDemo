package com.demo.hzed.infrastructure.exception;


import com.demo.hzed.infrastructure.enums.BizCodeEnum;

/**
 * 警告异常 不打error
 *
 * @author guichang
 * @since 2018/3/27
 */
public class WarnException extends BaseBizException {

    public WarnException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器 含错误码
     *
     * @param codeEnum 错误码
     */
    public WarnException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public WarnException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMsg(), extraMsg);
    }

    /**
     * 构造器 含错误码、异常
     *
     * @param codeEnum 错误码
     * @param cause    异常
     */
    public WarnException(String codeEnum, String cause) {
        super(codeEnum, cause);
    }

    public WarnException(BizCodeEnum codeEnum, Throwable cause, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMsg(), cause, extraMsg);
    }


}
