package com.hzed.easyget.infrastructure.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局请求头
 *
 * @author guichang
 */
@Slf4j
@Data
public class GlobalHeadr {
    private String i18n;
    private String appKey;
    private String platform;
    private String token;
    private String version;

}