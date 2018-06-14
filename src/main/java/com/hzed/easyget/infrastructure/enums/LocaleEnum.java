package com.hzed.easyget.infrastructure.enums;

import com.hzed.easyget.infrastructure.utils.ComUtil;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Locale;

/**
 * 地区枚举
 *
 * @author guichang
 */
@Getter
public enum LocaleEnum {
    /**
     * 过规则
     */
    id_ID("id-ID", "印尼语", ComUtil.IDLOCALE),
    zh_CN("zh-CN", "中文", Locale.CHINA),
    en_US("en-US", "英语", Locale.US);

    private String i18n;
    private String descr;
    private Locale locale;

    LocaleEnum(String i18n, String descr, Locale locale) {
        this.i18n = i18n;
        this.descr = descr;
        this.locale = locale;
    }

    public static Locale getLocale(String i18n) {
        if (StringUtils.isBlank(i18n)) {
            // 默认中文
            return zh_CN.getLocale();
        }

        for (LocaleEnum localeEnum : LocaleEnum.values()) {
            if (localeEnum.getI18n().equals(i18n)) {
                return localeEnum.getLocale();
            }
        }
        // 默认中文
        return zh_CN.getLocale();
    }
}
