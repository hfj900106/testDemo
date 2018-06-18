package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.service.I18nService;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截返回并封装成 com.hzed.easyget.infrastructure.model.Response
 *
 * @author guichang
 * @date 2018/6/18
 */
@Slf4j
@RestControllerAdvice
public class RespBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private I18nService i18nService;

    /**
     * 全部进入
     */
    @Override
    public boolean supports(MethodParameter parameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 返回结果转换
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter parameter,
                                  org.springframework.http.MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        Object result = body;
        ModuleFunc moduleFunc = RequestUtil.getModuleFunc();
        if (moduleFunc != null) {
            if (moduleFunc.isCommonResponse()) {
                Response resp = Response.getSuccessResponse(body);
                resp.setMessage(i18nService.getBizCodeMessage(resp.getCode()));
                result = resp;
            }
            if (moduleFunc.isParameterPrint()) {
                log.info("返回报文：{}", JSON.toJSONString(result));
            }
        }
        return result;
    }

}
