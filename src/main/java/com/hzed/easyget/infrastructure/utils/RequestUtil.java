package com.hzed.easyget.infrastructure.utils;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author guichang
 */
public class RequestUtil {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void render(Response result) throws IOException {
        HttpServletResponse response = getHttpServletResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
    }

    public static void renderError(String msg) throws IOException {
        Response.getFailResponse(msg);
    }


}