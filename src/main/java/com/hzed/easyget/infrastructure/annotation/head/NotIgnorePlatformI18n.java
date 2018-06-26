package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略Platform验证-根据需要自由组合请求头校验
 *
 * @author guichang
 * @date 2018/5/23
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AppkeyIgnore
@ImeiIgnore
@TokenIgnore
@VersionIgnore
public @interface NotIgnorePlatformI18n {

}
