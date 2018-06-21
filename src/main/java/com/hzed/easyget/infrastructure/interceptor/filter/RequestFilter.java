package com.hzed.easyget.infrastructure.interceptor.filter;

import com.hzed.easyget.infrastructure.enums.LocaleEnum;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * 过滤器，用户替换request
 *
 * @author guichang
 */
@Slf4j
@WebFilter(filterName = "requestFilter", urlPatterns = {"/api/*", "/callback/*"})
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            // 日志加入trace
            MdcUtil.putTrace();
            // local替换成请求头中的
            Locale locale = LocaleEnum.getLocale(RequestUtil.getGlobalHead().getI18n());
            Locale.setDefault(locale);
            RequestWrapper request = new RequestWrapper((HttpServletRequest) servletRequest, locale);
            log.info("请求URL：{}", request.getRequestURL());
            request.setAttribute("body", request.getBody());
            filterChain.doFilter(request, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}