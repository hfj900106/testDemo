package com.hzed.easyget.infrastructure.interceptor;

import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 统一请求拦截
 * @author guichang
 */
@Slf4j
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不为方法拦截直接放过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 日志加入trace
        MDC.put(LogConsts.TRACE, UUID.randomUUID().toString().replaceAll("-", "").substring(3, 20));

        // 方法拦截
        HandlerMethod mHandler = (HandlerMethod) handler;

        // 忽略Token验证
        TokenIgnore tokenIgnore = mHandler.getMethodAnnotation(TokenIgnore.class);
        if(tokenIgnore == null) {
            return true;
        }



        return true;
    }

}

