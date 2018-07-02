package com.hzed.easyget.infrastructure.config.rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setReadTimeout(120000);
        restTemplate.setRequestFactory(requestFactory);
    }

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
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            httpPost.setHeader("Content-Type", "appplication/json");
            if (StringUtils.isNotBlank(json)) {
                httpPost.setEntity(new StringEntity(json, "utf-8"));
            }
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(8000).setSocketTimeout(8000).setConnectTimeout(8000).build();
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = getHttpClient().execute(httpPost);
            if (response != null) {
                org.apache.http.HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "TIMEOUT";
        } finally {
            httpPost.releaseConnection();
        }
        return result;

    }

    /**
     * 获得一个连接对象
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        // ServiceUnavailableRetryStrategy表示在服务暂时不可用时确定是否应该重试请求的响应策略
        ServiceUnavailableRetryStrategy strategy = new ServiceUnavailableRetryStrategy() {
            /**
             * retry逻辑 重试三次
             */
            @Override
            public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                if (executionCount <= 3) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * retry间隔时间
             */
            @Override
            public long getRetryInterval() {
                return 1000;
            }
        };
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(100);
        // DefaultHttpRequestRetryHandler表示一种策略，用于确定在发生I/O错误（从服务器未收到HTTP响应）时请求是否可以安全重试。
        CloseableHttpClient httpClient = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler())
                .setConnectionManager(cm).build();
        return httpClient;
    }


}
