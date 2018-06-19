package com.hzed.easyget.infrastructure.interceptor.filter;

import lombok.Data;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Locale;

/**
 * 自定义request实现
 * 1、读到request中的json并写出
 * 2、重写getLocale()方法
 *
 * @author guichang
 * @date 2018/6/19
 */
@Data
public class RequestWrapper extends HttpServletRequestWrapper {
    private final String body;
    private Locale locale;

    public RequestWrapper(HttpServletRequest request, Locale locale) throws IOException {
        super(request);
        this.locale = locale;

        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = request.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesRead);
        }
        bufferedReader.close();
        // 替换换行符和回车符
        body = stringBuilder.toString().replaceAll("\n|\t", "");
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

}