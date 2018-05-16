package com.hzed.easyget.infrastructure.exception;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;

/**
 * 内部异常 不对外
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
        super(codeEnum.getCode(), codeEnum.getMessage());
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public NestedException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMessage(), extraMsg);
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
        super(codeEnum.getCode(), codeEnum.getMessage(), cause, extraMsg);
    }


}
