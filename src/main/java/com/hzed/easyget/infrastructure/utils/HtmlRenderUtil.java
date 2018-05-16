package com.hzed.easyget.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class HtmlRenderUtil {

    public static String renderUrlHtml(String url) {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>加载中</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4 style=\"font-size:3em; text-align:center\">处理中...</h4>\n" +
                "<script language=\"javascript\">location.href='" + url + "';</script></body>\n" +
                "</html>";
        return s;
    }

    public static String renderErrorHtml(String msg) {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>" + msg + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4 style=\"font-size:3em; text-align:center\">" + msg + "</h4>\n" +
                "</body>\n" +
                "</html>";
        return s;
    }

    public static String postUrlHtml(String url, Map<String, String> params) {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>加载中</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4 style=\"font-size:3em; text-align:center\">处理中...</h4>\n" +
                "<form action=\"" + url + "\" id=\"frm1\" method=\"post\">\n";

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                s += "<input type=\"hidden\" name=\"" + key + "\" value=\"" + params.get(key) + "\" />\n";
            }
        }
        s += "</form><script language=\"javascript\">document.getElementById(\"frm1\").submit();</script></html>";
        return s;
    }

}
