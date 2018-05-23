package com.hzed.easyget.infrastructure.interceptor;

import com.hzed.easyget.infrastructure.annotation.HeaderIgnore;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.consts.LogConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 日志加入trace
        MDC.put(LogConsts.TRACE, UUID.randomUUID().toString().replaceAll("-", "").substring(3, 20));

        // 不为方法拦截直接放过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod mHandler = (HandlerMethod) handler;

        // 请求头忽略标志
        HeaderIgnore headerIgnore = mHandler.getMethodAnnotation(HeaderIgnore.class);
        if(headerIgnore != null) {
            return true;
        }

        // header中一些必要参数校验
        GlobalHeadr globalHead = RequestUtil.getGlobalHead();

        if (StringUtils.isBlank(globalHead.getAppKey())) {
            RequestUtil.render(BizCodeEnum.ILLEGAL_APPKEY);
            return false;
        }
        if (StringUtils.isBlank(globalHead.getPlatform())) {
            RequestUtil.render(BizCodeEnum.ILLEGAL_PLATFORM);
            return false;
        }
        if (StringUtils.isBlank(globalHead.getVersion())) {
            RequestUtil.render(BizCodeEnum.ILLEGAL_VERSION);
            return false;
        }
        if (StringUtils.isBlank(globalHead.getI18n())) {
            RequestUtil.render(BizCodeEnum.ILLEGAL_I18N);
            return false;
        }

        // token验证
        TokenIgnore tokenIgnore = mHandler.getMethodAnnotation(TokenIgnore.class);
        if (tokenIgnore == null) {
            String token = globalHead.getToken();
            if (StringUtils.isBlank(token)) {
                RequestUtil.render(BizCodeEnum.ILLEGAL_TOKEN);
                return false;
            }

            GlobalUser globalUser = JwtUtil.verify(token, GlobalUser.class);
            if (globalUser == null) {
                RequestUtil.render(BizCodeEnum.ILLEGAL_TOKEN);
                return false;
            }
        }

        return true;
    }

}

