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

    public ComBizException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getMessage());
    }

    public ComBizException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(), codeEnum.getMessage(), extraMsg);
    }

    public ComBizException(BizCodeEnum codeEnum, Object data) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, null);
    }

    public ComBizException(BizCodeEnum codeEnum, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), null, objs);
    }

    public ComBizException(BizCodeEnum codeEnum, Object data, Object[] objs) {
        super(codeEnum.getCode(), codeEnum.getMessage(), data, objs);
    }



}
