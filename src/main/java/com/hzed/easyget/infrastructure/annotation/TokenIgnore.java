package com.hzed.easyget.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author guichang
 * @date 2018/05/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenIgnore {

}
