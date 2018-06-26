package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略所有请求头验证
 *
 * @author guichang
 * @date 2018/5/23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AppkeyIgnore
@ImeiIgnore
@TokenIgnore
@VersionIgnore
@PlatformIgnore
@I18nIgnore
public @interface IgnoreHeader {

}
