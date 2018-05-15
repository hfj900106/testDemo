package com.demo.hzed.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标志controller中方法的用处
 * @author guichang
 * @since 2017/11/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleLog {
    String value() default "";
    // 是否在logback日志中显示功能名模块
    boolean moduleNameFlag() default true;
}