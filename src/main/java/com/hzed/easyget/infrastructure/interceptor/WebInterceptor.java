package com.hzed.easyget.infrastructure.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.*;
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

        GlobalHead globalHeadr = RequestUtil.getGlobalHead();
        log.info("请求头信息：{}", JSON.toJSONString(globalHeadr));

        // 校验token
        TokenIgnore tokenIgnore = mHandler.getMethodAnnotation(TokenIgnore.class);
        if (tokenIgnore == null) {
            comService.validateToken(globalHeadr);
            log.info("用户信息：{}", JSON.toJSONString(RequestUtil.getGlobalUser()));
        }
        // 校验platform
        PlatformIgnore platformIgnore = mHandler.getMethodAnnotation(PlatformIgnore.class);
        if (platformIgnore == null) {
            globalHeadr.validatePlatform();
        }
        // 校验imei
        ImeiIgnore imeiIgnore = mHandler.getMethodAnnotation(ImeiIgnore.class);
        if (imeiIgnore == null) {
            globalHeadr.validImei();
        }
        // 校验i18n
        I18nIgnore i18nIgnore = mHandler.getMethodAnnotation(I18nIgnore.class);
        if (i18nIgnore == null) {
            globalHeadr.validateI18n();
        }
        // 校验version
        VersionIgnore versionIgnore = mHandler.getMethodAnnotation(VersionIgnore.class);
        if (versionIgnore == null) {
            globalHeadr.validateVersion();
        }
        // 校验appkey
        AppkeyIgnore appkeyIgnore = mHandler.getMethodAnnotation(AppkeyIgnore.class);
        if (appkeyIgnore == null) {
            globalHeadr.validateAppkey();
        }


        return true;
    }

}
