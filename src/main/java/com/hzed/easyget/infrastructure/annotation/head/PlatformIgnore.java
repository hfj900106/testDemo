package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略Platform验证
 *
 * @author guichang
 * @date 2018/05/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PlatformIgnore {

}
