package com.hzed.easyget.infrastructure.config.redis;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis相关操作类
 *
 * @author guichang
 * @date 2018/5/22
 */
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setCache(String key, Object value, Long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Boolean setIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public <T> T getCache(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public void clearCache(String key) {
        redisTemplate.delete(key);
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
        if (setIfAbsent(key, 0)) {
            // 设置超时时间
            expire(key, 3, TimeUnit.MINUTES);
            return false;
        }
        return true;
    }


}
