package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.service.I18nService;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * controller方法统一拦截
 *
 * @author guichang
 * @date 2018/05/16
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private I18nService i18nService;

    @Around("@annotation(moduleFunc)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, ModuleFunc moduleFunc) throws Throwable {
        MDC.put(LogConsts.MODULE_NAME, moduleFunc.value());

        // 打印请求和返回参数的标志
        boolean printParameter = moduleFunc.isParameterPrint();

        // 请求参数
        Object[] args = joinPoint.getArgs();

        // 打印请求报文
        if (printParameter) {
            log.info("请求报文：{}", JSON.toJSONString(args.length == 0 ? "无请求参数" : (args.length == 1 ? args[0] : args)));
        }

        // 执行请求
        Object result = joinPoint.proceed();

        // 打印返回报文
        if (printParameter) {
            log.info("返回报文：{}", JSON.toJSONString(result));
        }
        // 统一做国际化处理
        if (result instanceof Response) {
            Response resp = (Response) result;
            resp.setMessage(i18nService.getBizCodeMessage(resp.getCode(), null));
            result = resp;
        }
        return result;
    }


}