package com.hzed.easyget.infrastructure.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 *
 * @author guichang
 * @since 2017/11/29
 */
public class RestConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(30000);
        factory.setConnectTimeout(30000);
        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }
}
