package com.hzed.easyget.infrastructure.annotation.head;

import java.lang.annotation.*;

/**
 * 忽略Imei（用户设备唯一标识）验证
 *
 * @author guichang
 * @date 2018/05/16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImeiIgnore {

}