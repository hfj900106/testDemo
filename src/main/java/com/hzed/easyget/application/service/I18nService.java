package com.hzed.easyget.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@Service
public class I18nService {

    @Autowired
    private MessageSource messageSource;

    /**
     * 根据资源文件的key拿到内容
     * @param bizCode BizCodeEnum中的code
     */
    public String getBizCodeMessage(String bizCode, Object[] objs) {
        String key = "bizCode." + bizCode;
        return getMessage(key, objs);
    }

    public String getBizCodeMessage(String bizCode, Object[] objs, Locale locale) {
        String key = "bizCode." + bizCode;
        return getMessage(key, objs, locale);
    }

    /**
     * 根据资源文件的key拿到内容
     * @param key key
     */
    public String getMessage(String key, Object[] objs) {
        return getMessage(key, objs, Locale.getDefault());
    }

    public String getMessage(String key, Object[] objs, Locale locale) {
        return messageSource.getMessage(key, objs, locale);
    }
}
