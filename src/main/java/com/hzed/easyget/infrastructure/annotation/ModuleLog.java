package com.hzed.easyget.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标志controller中方法的用处
 *
 * @author guichang
 * @date 2018/05/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleLog {
    String value() default "";

    // 是否在logback日志中显示功能名模块
    boolean moduleNameFlag() default true;
}