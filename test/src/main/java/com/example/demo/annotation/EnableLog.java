package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author hfj
 * @date 2019/3/27
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableLog {
    String value() default "";
}
