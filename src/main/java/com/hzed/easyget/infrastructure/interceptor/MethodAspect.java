package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import com.hzed.easyget.infrastructure.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
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
public class MethodAspect {

    @Around("@annotation(moduleFunc)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, ModuleFunc moduleFunc) throws Throwable {
        MDC.put(LogConsts.MODULE_NAME, moduleFunc.value());

        // 打印请求和返回参数的标志
        boolean printParameter = moduleFunc.isParameterPrint();
        // 校验请求参数的标志
        boolean parameterValidate = moduleFunc.isParameterValidate();

        // 请求参数
        Object[] args = joinPoint.getArgs();

        // 打印请求报文
        if (printParameter) {
            log.info("请求报文：{}", JSON.toJSONString(args.length == 0 ? "无请求参数" : (args.length == 1 ? args[0] : args)));
        }

        // 校验请求参数
        if (parameterValidate) {
            for (Object obj : args) {
                ValidatorUtil.validateWithNull(obj);
            }
        }

        // 执行请求
        Object result = joinPoint.proceed();

        // 打印返回报文
        if (printParameter) {
            log.info("返回报文：{}", JSON.toJSONString(result));
        }
        return result;
    }


}