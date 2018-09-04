package com.hzed.easyget.infrastructure.annotation;

import com.hzed.easyget.infrastructure.config.redis.RedisConfig;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({RedisService.class, RedisConfig.class})
public @interface EnableRedis {
}
