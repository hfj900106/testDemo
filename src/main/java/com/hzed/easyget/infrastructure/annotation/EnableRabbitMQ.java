package com.hzed.easyget.infrastructure.annotation;

import com.hzed.easyget.application.mq.MqConsumer;
import com.hzed.easyget.infrastructure.config.rabbit.RabbitConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({MqConsumer.class, RabbitConfig.class})
public @interface EnableRabbitMQ {
}
