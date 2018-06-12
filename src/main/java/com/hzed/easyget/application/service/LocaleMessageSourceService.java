package com.hzed.easyget.application.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@Component
public class LocaleMessageSourceService {
    public static final ThreadLocal<Locale> LOCALES =  new ThreadLocal<>();
    public static final Locale ind = new Locale("id", "ID");

    @Resource
    private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LOCALES.get());
    }
}
