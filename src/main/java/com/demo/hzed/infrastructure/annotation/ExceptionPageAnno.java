package com.demo.hzed.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记异常为 PageExpHandlerAdvice 处理
 *
 * @author guichang
 * @since 2018/3/16
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionPageAnno {
    String value() default "";
}