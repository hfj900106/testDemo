package com.demo.hzed.infrastructure.exception;

import com.demo.hzed.infrastructure.enums.BizCodeEnum;

/**
 * 内部异常
 *
 * @author guichang
 * @since 2017/11/18
 */
public class NestedException extends BaseBizException {

    public NestedException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造器 含错误码
     *
     * @param codeEnum 错误码
     */
    public NestedException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public NestedException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMsg(), extraMsg);
    }

    /**
     * 构造器 含错误码、异常
     *
     * @param codeEnum 错误码
     * @param cause    异常
     */
    public NestedException(String codeEnum, String cause) {
        super(codeEnum, cause);
    }

    public NestedException(BizCodeEnum codeEnum, Throwable cause, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMsg(), cause, extraMsg);
    }


}
