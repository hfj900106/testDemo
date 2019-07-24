package com.example.demo.aop;

import com.example.demo.annotation.EnableLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author hfj
 * @date 2019/3/27
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    //    @Pointcut("execution(public * com.example.demo.service..*.*(..))") // 包
//    @Pointcut("execution(public * com.example.demo.service.Teacher.*(..))") // 类
    @Pointcut("execution(public * com.example.demo.service.Teacher.dance())") // 类方法
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("@annotation(enableLog)")
    public Object aroundJob(ProceedingJoinPoint pPoint, EnableLog enableLog) throws Throwable {
        String val = enableLog.value();
///        String methodName = ((MethodSignature) pPoint.getSignature()).getMethod().getName();
        System.out.println(val);
        return pPoint.proceed();
    }
}
