package com.hzed.easyget.infrastructure.exception;


import com.hzed.easyget.infrastructure.enums.BizCodeEnum;

public class ComBizException extends BaseBizException {

    /**
     * 构造器 含错误码
     *
     * @param codeEnum 错误码
     */
    public ComBizException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(),codeEnum.getMsg());
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public ComBizException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(),codeEnum.getMsg(), extraMsg);
    }

    /**
     * 构造器 含错误码、异常
     *
     * @param codeEnum 错误码
     * @param cause    异常
     */
    public ComBizException(String codeEnum, String cause) {
        super(codeEnum, cause);
    }

    public ComBizException( String cause) {
        super(BizCodeEnum.SERVICE_EXCEPTION.getCode(), cause);
    }

}
