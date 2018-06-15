package com.hzed.easyget.infrastructure.interceptor.filter;

import com.hzed.easyget.infrastructure.enums.LocaleEnum;
import com.hzed.easyget.infrastructure.utils.RequestUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * 过过滤器，用户替换request
 *
 * @author guichang
 */
@WebFilter(filterName = "requestFilter", urlPatterns = {"/api/*"})
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            // local替换成请求头中的
            Locale locale = LocaleEnum.getLocale(RequestUtil.getGlobalHead().getI18n());
            Locale.setDefault(locale);
            filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest, locale), servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}