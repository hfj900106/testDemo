package com.hzed.easyget.infrastructure.config.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化国际化配置
 *
 * @author Waylon
 * @date 2018/6/7
 */

@Configuration
public class MessageConfig {

    /** 国际化资源文件路径 */
    @Value("${spring.messages.basename}")
    private String basename;
    /** 资源文件刷新周期 */
    @Value("${spring.messages.cacheSeconds}")
    private Integer cacheSeconds;

    @Value("${spring.messages.encoding}")
    private String encoding;

    /*@Primary
    @Bean("myMessageSource")
    public MessageSource buildMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheSeconds(3);
        return messageSource;
    }*/
}
