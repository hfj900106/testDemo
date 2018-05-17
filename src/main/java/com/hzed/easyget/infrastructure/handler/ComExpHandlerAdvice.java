package com.hzed.easyget.infrastructure.handler;

import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleAnno;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * @author guichang
 */
@Slf4j
@RestControllerAdvice(annotations = ExceptionAnno.class)
public class ComExpHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Response handler(Exception ex, HandlerMethod handler) {

        // 模块名
        String moduleLog = "";
        ModuleAnno moduleDetailLog = handler.getMethodAnnotation(ModuleAnno.class);
        if (moduleDetailLog != null) {
            moduleLog = moduleDetailLog.value();
        }

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