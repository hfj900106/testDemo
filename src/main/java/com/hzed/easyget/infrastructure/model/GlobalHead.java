package com.hzed.easyget.infrastructure.model;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 全局请求头
 *
 * @author guichang
 */
@Slf4j
@Data
public class GlobalHead {
    private String imei;
    private String i18n;
    private String appKey;
    private String platform;
    private String token;
    private String version;

    /**
     * 校验imei
     */
    public void validImei() {
        if (StringUtils.isBlank(imei)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_IMEI);
        }
    }
    /**
     * 校验appkey
     */
    public void validateAppkey() {
        if (StringUtils.isBlank(appKey)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_APPKEY);
        }
    }

    /**
     * 校验platform
     */
    public void validatePlatform() {
        if (StringUtils.isBlank(platform)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_PLATFORM);
        }
    }

    /**
     * 校验version
     */
    public void validateVersion() {
        if (StringUtils.isBlank(version)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_VERSION);
        }
    }

    /**
     * 校验i18n
     */
    public void validateI18n() {
        if (StringUtils.isBlank(i18n)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_I18N);
        }
    }

}