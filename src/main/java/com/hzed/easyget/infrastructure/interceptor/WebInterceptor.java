package com.hzed.easyget.infrastructure.interceptor;

import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.infrastructure.annotation.HeaderIgnore;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 统一请求拦截
 *
 * @author guichang
 */
@Slf4j
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ComService comService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 日志加入trace
        MDC.put(LogConsts.TRACE, UUID.randomUUID().toString().replaceAll("-", "").substring(3, 20));

        // 不为方法拦截直接放过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod mHandler = (HandlerMethod) handler;

        // 请求头忽略标志
        HeaderIgnore headerIgnore = mHandler.getMethodAnnotation(HeaderIgnore.class);
        if (headerIgnore != null) {
            return true;
        }

        GlobalHead globalHeadr = RequestUtil.getGlobalHead();
        // header中一些必要参数校验
        comService.validateHeader(globalHeadr);

        // token验证
        TokenIgnore tokenIgnore = mHandler.getMethodAnnotation(TokenIgnore.class);
        if (tokenIgnore == null) {
            comService.validateToken(globalHeadr);
        }
        return true;
    }

}
