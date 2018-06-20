package com.hzed.easyget.infrastructure.interceptor;

import com.hzed.easyget.infrastructure.annotation.JobAnnotation;
import com.hzed.easyget.infrastructure.config.JobProp;
import com.hzed.easyget.infrastructure.utils.ComUtil;
import com.hzed.easyget.infrastructure.utils.MDCUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定时任务统一拦截
 *
 * @author guichang
 * @since 2018/2/5
 */
@Slf4j
@Aspect
@Component
public class JobAspect {

    @Autowired
    private JobProp jobProp;

    /**
     * 拦截定时任务主要处理类
     */
    @Around("@annotation(jobAnnotation)")
    public Object aroundTest(ProceedingJoinPoint pPoint, JobAnnotation jobAnnotation) throws Throwable {
        String methodName = ((MethodSignature) pPoint.getSignature()).getMethod().getName();
        // 定时任务名放入日志中
        MDCUtil.put("moduleName", jobAnnotation.value());
        // 如果配置不执行或定时任务正在进行直接返回
        if (!getRunFlag(methodName)) {
            return null;
        }
        // 设置运行标志-false
        setRunFlag(methodName, false);

        log.info("=====定时任务 开始 =====");
        // 每个任务自己处理异常
        Object result = pPoint.proceed();
        log.info("=====定时任务 结束 =====");
        // 设置运行标志-true
        setRunFlag(methodName, true);
        return result;
    }

    /**
     * 根据方法名获取运行标志
     */
    private boolean getRunFlag(String paraName) {
        try {
            Method a = JobProp.class.getMethod("is" + ComUtil.toUpperCaseIndex(paraName));
            Boolean invoke = (Boolean) a.invoke(jobProp);
            return invoke.booleanValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 设置运行标志
     */
    private void setRunFlag(String paraName, boolean flag) {
        try {
            for (Method method : JobProp.class.getMethods()) {
                if (method.getName().equals("set" + ComUtil.toUpperCaseIndex(paraName))) {
                    method.invoke(jobProp, flag);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}