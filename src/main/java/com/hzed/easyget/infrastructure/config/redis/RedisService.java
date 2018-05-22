package com.hzed.easyget.infrastructure.config.redis;

import com.hzed.easyget.infrastructure.consts.RedisConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis相关操作类
 * @author guichang
 * @date 2018/5/22
 */
@Component
public class RedisService {

    @Value("${spring.profiles.active}")
    private String env;
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private StringRedisTemplate sTemplate;

    public void setCache(String key, String value, Long seconds) {
        sTemplate.opsForValue().set(getKey(key), value, seconds, TimeUnit.SECONDS);
    }

    public void setCache(String key, String value) {
        sTemplate.opsForValue().set(getKey(key), value);
    }

    public void incrCache(String key, long delta) {
        sTemplate.opsForValue().increment(getKey(key), delta);
    }

    public Boolean setIfAbsent(String key, String value) {
        return sTemplate.opsForValue().setIfAbsent(getKey(key), value);
    }

    public String getCache(String key) {
        return sTemplate.opsForValue().get(getKey(key));
    }

    public void clearCache(String key) {
        sTemplate.delete(getKey(key));
    }

    private String getKey(String key) {
        return appName + RedisConsts.SPLIT + env + RedisConsts.SPLIT + key;
    }


}
