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

import java.util.List;

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

    @ExceptionHandler(Exception.class)
    public Response handler(Exception ex) {

        Response resp = Response.getFailResponse();
        if (ex instanceof ComBizException) {
            ComBizException cbEx = (ComBizException) ex;
            log.error("业务异常：{}", cbEx.getSerializeMsg(), cbEx);
            resp.setCode(cbEx.getErrorCode());
            resp.setMessage(cbEx.getSerializeMsg());
        } else if (ex instanceof WarnException) {
            WarnException wEx = (WarnException) ex;
            log.warn("业务警告：{}", wEx.getSerializeMsg(), wEx);
            resp.setCode(wEx.getErrorCode());
            resp.setMessage(wEx.getSerializeMsg());
        } else if (ex instanceof NestedException) {
            NestedException nEx = (NestedException) ex;
            log.warn("内部异常：{}", nEx.getSerializeMsg());
            resp.setCode(nEx.getErrorCode());
            resp.setMessage(nEx.getSerializeMsg());
        } else {
            // 单独处理不传请求参数的情况
            String requiredRequestBodyIsMissing = "Required request body is missing";
            if (ex.toString().indexOf(requiredRequestBodyIsMissing) > 0) {
                resp.setCode(BizCodeEnum.ILLEGAL_PARAM.getCode());
                resp.setMessage(BizCodeEnum.ILLEGAL_PARAM.getMessage());
            }

            log.error("其他异常：", ex);
        }


        // 统一做国际化处理
        resp.setMessage(i18nService.getBizCodeMessage(resp.getCode()));

        return resp;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handlerComBizException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return new Response(BizCodeEnum.ILLEGAL_PARAM.getCode(), errorMsg.toString());
    }

//    @ExceptionHandler(ComBizException.class)
//    public Response handlerComBizException(ComBizException cbEx) {
//        log.error("业务异常：{}", cbEx.getSerializeMsg(), cbEx);
//        return new Response(cbEx.getErrorCode(), cbEx.getSerializeMsg());
//    }
//
//    @ExceptionHandler(WarnException.class)
//    public Response handlerWarnException(WarnException wEx) {
//        log.warn("业务警告：{}", wEx.getSerializeMsg(), wEx);
//        return new Response(wEx.getErrorCode(), wEx.getSerializeMsg());
//    }
//
//    @ExceptionHandler(NestedException.class)
//    public Response handlerNestedException(NestedException nEx) {
//        log.warn("内部异常：{}", nEx.getSerializeMsg());
//        return new Response(nEx.getErrorCode(), nEx.getSerializeMsg());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Response handlerException(Exception ex) {
//        Response resp = Response.getFailResponse();
//        // 单独处理不传请求参数的情况
//        String requiredRequestBodyIsMissing = "Required request body is missing";
//        if (ex.toString().indexOf(requiredRequestBodyIsMissing) > 0) {
//            resp.setCode(BizCodeEnum.ILLEGAL_PARAM.getCode());
//            resp.setMessage(BizCodeEnum.ILLEGAL_PARAM.getMessage());
//        }
//
//        log.error("其他异常：", ex);
//
//        return resp;
//    }

}