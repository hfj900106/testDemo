package com.hzed.easyget.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 配置此注解即可拦截定时任务
 * @author guichang
 * @since 2018/2/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JobAnnotation {
    String value();

}
