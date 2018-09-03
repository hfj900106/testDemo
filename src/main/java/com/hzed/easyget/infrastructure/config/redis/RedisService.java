package com.hzed.easyget.infrastructure.config.redis;

import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis相关操作类
 *
 * @author guichang
 * @date 2018/5/22
 */
public class RedisService {

    @Value("${spring.profiles.active}")
    private String env;
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private StringRedisTemplate sTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public void setCache(String key, String value, Long seconds) {
        sTemplate.opsForValue().set(getKey(key), value, seconds, TimeUnit.SECONDS);
    }

    public void setObjCache(String key, Object value, Long seconds) {
        redisTemplate.opsForValue().set(getKey(key), value, seconds, TimeUnit.SECONDS);
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

    public <T> T getObjCache(String key) {
        Object o = redisTemplate.opsForValue().get(getKey(key));
        return (T) o;
    }

    public void expire(String key, long timeout, TimeUnit unit) {
        sTemplate.expire(getKey(key), timeout, unit);
    }

    public void clearCache(String key) {
        sTemplate.delete(getKey(key));
        redisTemplate.delete(getKey(key));
    }

    private String getKey(String key) {
        return appName + RedisConsts.SPLIT + env + RedisConsts.SPLIT + key;
    }

    /**
     * redis防重
     *
     * @param key     key值
     * @param expEnum 重复后抛异常的枚举
     */
    public void defensiveRepet(String key, BizCodeEnum expEnum) {
        if (exists(key)) {
            // 插入失败直接抛异常
            throw new WarnException(expEnum);
        }
    }

    /**
     * redis中key是否存在
     *
     * @param key
     * @return true-存在 false-不存在
     */
    public boolean exists(String key) {
        if (setIfAbsent(key, String.valueOf(0))) {
            // 设置超时时间
            expire(key, 3, TimeUnit.MINUTES);
            return false;
        }
        return true;
    }

}
