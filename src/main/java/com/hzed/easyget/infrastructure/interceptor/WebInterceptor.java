package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.infrastructure.annotation.HeaderIgnore;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.utils.ComUtil;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        MdcUtil.putTrace();

        // 不为方法拦截直接放过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod mHandler = (HandlerMethod) handler;

        // 处理moduleFunc
        ModuleFunc moduleFunc = mHandler.getMethodAnnotation(ModuleFunc.class);
        // 放入request中，方便后续拿出来
        RequestUtil.setModuleFunc(moduleFunc);
        if (moduleFunc != null) {
            // 设置模块名
            MdcUtil.putModuleName(moduleFunc.value());
            String body = ComUtil.subString(request.getAttribute("body").toString(), moduleFunc.printParameterLength());
            log.info("请求报文：{}", StringUtils.isBlank(body) ? "无请求参数" : body);
        }

        // 请求头忽略标志
        HeaderIgnore headerIgnore = mHandler.getMethodAnnotation(HeaderIgnore.class);
        if (headerIgnore != null) {
            return true;
        }

        GlobalHead globalHeadr = RequestUtil.getGlobalHead();
        log.info("请求头信息：{}", JSON.toJSONString(globalHeadr));
        // header中一些必要参数校验
        comService.validateHeader(globalHeadr);

        // token验证
        TokenIgnore tokenIgnore = mHandler.getMethodAnnotation(TokenIgnore.class);
        if (tokenIgnore != null) {
            return true;
        }

        // 校验token
        comService.validateToken(globalHeadr);
        log.info("用户信息：{}", JSON.toJSONString(RequestUtil.getGlobalUser()));

        return true;
    }

}
