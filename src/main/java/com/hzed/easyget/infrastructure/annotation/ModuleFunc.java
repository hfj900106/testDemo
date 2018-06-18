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
public @interface ModuleFunc {
    /**
     * 模块名
     */
    String value() default "未命名模块";

    /**
     * 打印请求和返回参数的标志 默认true-打印
     */
    boolean isParameterPrint() default true;

    /**
     * 是否转换为com.hzed.easyget.infrastructure.model.Response
     * 默认 true-转换
     */
    boolean isCommonResponse() default true;

}