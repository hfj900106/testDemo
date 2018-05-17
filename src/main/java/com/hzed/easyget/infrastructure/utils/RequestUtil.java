package com.hzed.easyget.infrastructure.utils;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public static void render(Response result) throws Exception {
        HttpServletResponse response = getHttpServletResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
    }

    public static void render(BizCodeEnum codeEnum) throws Exception {
        render(new Response(codeEnum.getCode(), codeEnum.getMessage()));
    }

    public static GlobalHeadr getGlobalHead() {
        HttpServletRequest request = getHttpServletRequest();
        GlobalHeadr header = new GlobalHeadr();
        header.setAppKey(request.getHeader(ComConsts.APPKEY));
        header.setPlatform(request.getHeader(ComConsts.PLATFORM));
        header.setToken(request.getHeader(ComConsts.TOKEN));
        header.setVersion(request.getHeader(ComConsts.VERSION));
        return header;
    }


}