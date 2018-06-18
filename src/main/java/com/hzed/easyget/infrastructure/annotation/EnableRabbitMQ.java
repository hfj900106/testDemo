package com.hzed.easyget.infrastructure.annotation;

import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ImportResource(locations= {"classpath:config/rabbitMQ.xml"})
public @interface EnableRabbitMQ {
}
