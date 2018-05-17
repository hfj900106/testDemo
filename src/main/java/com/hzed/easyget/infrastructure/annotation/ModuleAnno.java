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
public @interface ModuleAnno {
    // 模块名
    String value() default "未命名模块";
    // 打印请求和返回参数的标志 默认打印
    boolean isPrintParameter() default true;
}