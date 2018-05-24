package com.hzed.easyget.infrastructure.annotation;

import com.hzed.easyget.infrastructure.config.rest.RestConfiguration;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({RestConfiguration.class, RestService.class})
public @interface EnableRest {
}
