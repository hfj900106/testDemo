package com.hzed.easyget.infrastructure.config.rest;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * RestTemplateService
 *
 * @author guichang
 */
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * post form请求
     *
     * @param url    请求地址
     * @param params 参数对象
     * @param clazz  方法返回类型
     * @return <T>T
     **/
    public <T> T postForm(String url, MultiValueMap<String, String> params, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Map<String, String>> request = new HttpEntity(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
        return responseEntity.getBody();
    }

    /**
     * post json 请求
     *
     * @param url       请求地址
     * @param parameter 参数对象
     * @param clazz     方法返回类型
     * @return <T>T
     **/
    public <T> T postJson(String url, Object parameter, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(parameter, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, entity, clazz);
        return responseEntity.getBody();
    }

    /**
     * get请求
     *
     * @param url    请求地址
     * @param params 参数对象
     * @param clazz  方法返回类型
     * @return <T>T
     **/
    public <T> T get(String url, Map<String, String> params, Class<T> clazz) {

        Set ketSet = params.keySet();
        StringBuffer paramsBuff = new StringBuffer();
        if (params != null && !params.isEmpty()) {
            Iterator<String> it = ketSet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                paramsBuff.append(key).append("=").append(value).append("&");
            }
        }
        String param;
        if (paramsBuff.length() > 0) {
            param = paramsBuff.substring(0, paramsBuff.length() - 1);
        } else {
            param = paramsBuff.toString();
        }
        url = url + "?" + param;
        T result = restTemplate.getForObject(url, clazz);
        return result;
    }

    /**
     * httpPost请求
     *
     * @param url  请求地址
     * @param json 请求参数
     * @return
     */
    public String doPostJson(String url, String json) {
        CloseableHttpClient http = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            httpPost.setHeader("Content-Type", "appplication/json");
            if (StringUtils.isNotBlank(json)) {
                httpPost.setEntity(new StringEntity(json, "utf-8"));
            }
            CloseableHttpResponse response = http.execute(httpPost);
            if (response != null) {
                org.apache.http.HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = BizCodeEnum.TIMEOUT.getCode();
        } finally {
            httpPost.releaseConnection();
        }
        return result;

    }
}
