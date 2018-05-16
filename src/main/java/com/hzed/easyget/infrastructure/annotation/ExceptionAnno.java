package com.hzed.easyget.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记异常为 ComExpHandlerAdvice 处理
 *
 * @author guichang
 * @since 2018/3/16
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionAnno {
    String value() default "";
}