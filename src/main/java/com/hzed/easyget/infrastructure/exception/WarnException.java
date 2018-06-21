package com.hzed.easyget.infrastructure.exception;


import com.hzed.easyget.infrastructure.enums.BizCodeEnum;

/**
 * 警告异常 不打error
 *
 * @author guichang
 * @since 2018/3/27
 */
public class WarnException extends BaseBizException {

    public WarnException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMessage());
    }

    public WarnException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMessage(), extraMsg);
    }

    public WarnException(BizCodeEnum codeEnum, Object data) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, null);
    }

    public WarnException(BizCodeEnum codeEnum, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), null, objs);
    }

    public WarnException(BizCodeEnum codeEnum, Object data, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, objs);
    }


}
