package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略Version（版本）验证
 *
 * @author guichang
 * @date 2018/05/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VersionIgnore {

}
