package com.hzed.easyget.infrastructure.exception;


import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import lombok.Getter;

/**
 * 业务异常，对外
 *
 * @author guichang
 * @since 2018/05/16
 */
@Getter
public class ComBizException extends BaseBizException {

    private Object[] objs;

    /**
     * 构造器 含错误码
     *
     * @param codeEnum 错误码
     */
    public ComBizException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMessage());
    }

    public ComBizException(BizCodeEnum codeEnum, Object... objs) {
        super(codeEnum.getCode(), codeEnum.getMessage());
        this.objs = objs;
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public ComBizException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMessage(), extraMsg);
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

    public ComBizException(String cause) {
        super(BizCodeEnum.SERVICE_EXCEPTION.getCode(), cause);
    }

}
