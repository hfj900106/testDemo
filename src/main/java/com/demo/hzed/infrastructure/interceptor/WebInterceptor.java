package com.demo.hzed.infrastructure.interceptor;

import com.demo.hzed.infrastructure.annotation.ModuleLog;
import com.demo.hzed.infrastructure.consts.LogConsts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 日志中增加功能名
        ModuleLog moduleDetailLog = ((HandlerMethod) handler).getMethodAnnotation(ModuleLog.class);
        if (moduleDetailLog != null) {
            MDC.put(LogConsts.MODULE_NAME, moduleDetailLog.value());
        }


        return true;
    }

//    private void render(HttpServletResponse response, Response result) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.write(JSON.toJSONString(result));
//    }
}

