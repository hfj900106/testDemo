package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略
 * Platform和i18n
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
public @interface IgnoreH5 {

}
