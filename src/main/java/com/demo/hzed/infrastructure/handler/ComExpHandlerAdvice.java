package com.demo.hzed.infrastructure.handler;

import com.demo.hzed.infrastructure.annotation.ExceptionAnno;
import com.demo.hzed.infrastructure.annotation.ModuleLog;
import com.demo.hzed.infrastructure.exception.ComBizException;
import com.demo.hzed.infrastructure.exception.NestedException;
import com.demo.hzed.infrastructure.model.Response;
import com.demo.hzed.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@RestControllerAdvice(annotations = ExceptionAnno.class)
public class ComExpHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Response handler(Exception ex, HandlerMethod handler) {

        String moduleLog = "";
        ModuleLog moduleDetailLog = handler.getMethodAnnotation(ModuleLog.class);
        if (moduleDetailLog != null) moduleLog = moduleDetailLog.value();

        Response resp = Response.getFailResponse();
        String servletPath = RequestUtil.getHttpServletRequest().getServletPath();
        if (ex instanceof ComBizException) {
            ComBizException cbEx = (ComBizException) ex;
            log.error("{}，请求url：{}，业务异常：{}", moduleLog, servletPath, cbEx.getSerializeMsg(), cbEx);
            resp.setCode(cbEx.getErrorCode());
            resp.setMessage(cbEx.getSerializeMsg());
        } else if (ex instanceof NestedException) {
            NestedException nEx = (NestedException) ex;
            log.warn("{}，请求url：{}，内部异常：{}", moduleLog, servletPath, nEx.getSerializeMsg());
            resp.setCode(nEx.getErrorCode());
            resp.setMessage(nEx.getSerializeMsg());
        } else {
            log.error("{}，请求url：{}，其他异常：", moduleLog, servletPath, ex);
        }
        return resp;
    }

}