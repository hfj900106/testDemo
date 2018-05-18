package com.hzed.easyget.infrastructure.handler;

import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * 拦截由ExceptionAnno标注的controller抛出的异常
 *
 * @author guichang
 */
@Slf4j
@RestControllerAdvice(annotations = ExceptionAnno.class)
public class ComExpHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Response handler(Exception ex, HandlerMethod handler) {

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
            String required_request_body_is_missing = "Required request body is missing";
            if (ex.toString().indexOf(required_request_body_is_missing) > 0) {
                resp.setCode(BizCodeEnum.ILLEGAL_PARAM.getCode());
                resp.setMessage(BizCodeEnum.ILLEGAL_PARAM.getMessage());
            }

            log.error("其他异常：", ex);
        }
        return resp;
    }

}