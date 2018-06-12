package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.enums.LocaleEnum;
import com.hzed.easyget.infrastructure.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@Service
public class I18nService {
    private static final String I18N = "i18n";

    @Autowired
    private MessageSource messageSource;

    /**
     * 根据资源文件的key拿到内容
     * @param bizCode BizCodeEnum中的code
     */
    public String getBizCodeMessage(String bizCode) {
        String key = "bizCode." + bizCode;


        return messageSource.getMessage(key, null, ThreadLocalUtil.get(I18N));
    }

    /**
     * 根据资源文件的key拿到内容
     * @param key key
     */
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, ThreadLocalUtil.get(I18N));
    }

    /**
     * 设置地域
     */
    public void setLocale(String i18n) {
        ThreadLocalUtil.set(I18N, LocaleEnum.getLocale(i18n));
    }
}
