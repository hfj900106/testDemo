package com.hzed.easyget.infrastructure.exception;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;

/**
 * 页面跳转异常
 *
 * @author wuchengwu
 * @since 2018/3/13
 */
public class PageException extends BaseBizException {
    

    /**
     * 构造器 含错误码
     *
     * @param codeEnum 错误码
     */
    public PageException(BizCodeEnum codeEnum) {
        super(codeEnum.getCode(),codeEnum.getMsg());
    }

    /**
     * 构造器 含错误码、错误描述
     *
     * @param codeEnum 错误码
     * @param extraMsg 错误描述
     */
    public PageException(BizCodeEnum codeEnum, String extraMsg) {
        super(codeEnum.getCode(),codeEnum.getMsg(), extraMsg);
    }

    /**
     * 构造器 含错误码、异常
     *
     * @param codeEnum 错误码
     * @param cause    异常
     */
    public PageException(String codeEnum, String cause) {
        super(codeEnum, cause);
    }

}