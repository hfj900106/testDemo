package com.hzed.easyget.infrastructure.interceptor.filter;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Locale;

/**
 * request替换类
 * @author guichang
 */
@Data
public class RequestWrapper extends HttpServletRequestWrapper {
    private Locale locale;

    public RequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}