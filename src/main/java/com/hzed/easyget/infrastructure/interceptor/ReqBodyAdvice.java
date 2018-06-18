package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Type;

/**
 * 拦截请求参数
 *
 * @author guichang
 * @date 2018/6/18
 */
@Slf4j
@RestControllerAdvice
public class ReqBodyAdvice implements RequestBodyAdvice {

    /**
     * 是否进入拦截
     */
    @Override
    public boolean supports(MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 有ModuleFunc这个注解就进去拦截
        ModuleFunc moduleFunc = parameter.getMethodAnnotation(ModuleFunc.class);
        // 放入request中，方便后续拿出来
        RequestUtil.setModuleFunc(moduleFunc);
        return moduleFunc == null ? false : true;
    }

    /**
     * 空请求处理
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 读请求前处理
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        ModuleFunc moduleFunc = RequestUtil.getModuleFunc();
        // 设置模块名
        MDC.put(LogConsts.MODULE_NAME, moduleFunc.value());
        return inputMessage;
    }

    /**
     * 读请求后处理 目前打印请求报文
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        int printParameterLength = RequestUtil.getModuleFunc().printParameterLength();
        if (printParameterLength > -1) {
            // 打印请求参数
            log.info("请求报文：{}", subString(JSON.toJSONString(body), printParameterLength));
        }

        return body;
    }

    private String subString(String str, int length) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else if (str.length() <= length) {
            return str;
        } else {
            return str.substring(0, length);
        }
    }
}
