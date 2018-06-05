package com.hzed.easyget.infrastructure.annotation;

import com.hzed.easyget.infrastructure.config.aliyun.AliyunConfig;
import com.hzed.easyget.infrastructure.config.aliyun.AliyunProp;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({AliyunProp.class, AliyunConfig.class})
public @interface EnableAliyun {
}
