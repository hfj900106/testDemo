package com.hzed.easyget.infrastructure.handler;

import com.hzed.easyget.application.service.I18nService;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Locale;

/**
 * 拦截由ExceptionAnno标注的controller抛出的异常
 *
 * @author guichang
 */
@Slf4j
@RestControllerAdvice(annotations = ExceptionAnno.class)
public class ExpHandlerAdvice {

    @Autowired
    private I18nService i18nService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HandlerMethod handler) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return new Response(BizCodeEnum.ILLEGAL_PARAM.getCode(), errorMsg.toString());
    }

    @ExceptionHandler(ComBizException.class)
    public Response handlerComBizException(ComBizException ex) {

        String errorCode = ex.getErrorCode();
        String message = i18nService.getBizCodeMessage(errorCode, ex.getObjs());
        // 中文描述 用于打印日志
        String messageCN = i18nService.getBizCodeMessage(errorCode, ex.getObjs(), Locale.CHINA);

        log.error("业务异常：{}", messageCN, ex);
        return new Response(errorCode, message, ex.getData());
    }

    @ExceptionHandler(WarnException.class)
    public Response handlerWarnException(WarnException ex) {

        String errorCode = ex.getErrorCode();
        String message = i18nService.getBizCodeMessage(errorCode, ex.getObjs());
        // 中文描述 用于打印日志
        String messageCN = i18nService.getBizCodeMessage(errorCode, ex.getObjs(), Locale.CHINA);

        log.warn("业务警告：{}", messageCN);
        return new Response(ex.getErrorCode(), message, ex.getData());
    }

    @ExceptionHandler(NestedException.class)
    public Response handlerNestedException(NestedException ex) {
        log.warn("内部异常：{}", ex.getMessage());
        return new Response(ex.getErrorCode(), i18nService.getBizCodeMessage(ex.getErrorCode()), ex.getData());
    }

    @ExceptionHandler(Exception.class)
    public Response handlerException(Exception ex) {
        Response resp = Response.getFailResponse();
        // 单独处理不传请求参数的情况
        String requiredRequestBodyIsMissing = "Required request body is missing";
        if (ex.toString().indexOf(requiredRequestBodyIsMissing) > 0) {
            resp.setCode(BizCodeEnum.ILLEGAL_PARAM.getCode());
            resp.setMessage(BizCodeEnum.ILLEGAL_PARAM.getMessage());
            log.warn("其他异常：", ex);
        } else {
            log.error("其他异常：", ex);
        }

        resp.setMessage(i18nService.getBizCodeMessage(resp.getCode()));
        return resp;
    }

}