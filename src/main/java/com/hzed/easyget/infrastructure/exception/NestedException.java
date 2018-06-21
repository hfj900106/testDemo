package com.hzed.easyget.infrastructure.exception;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;

/**
 * 内部异常 不对外
 *
 * @author guichang
 * @since 2017/11/18
 */
public class NestedException extends BaseBizException {

    public NestedException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMessage());
    }

    public NestedException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMessage(), extraMsg);
    }

    public NestedException(BizCodeEnum codeEnum, Object data) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, null);
    }

    public NestedException(BizCodeEnum codeEnum, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), null, objs);
    }

    public NestedException(BizCodeEnum codeEnum, Object data, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, objs);
    }


}
