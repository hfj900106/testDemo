package com.hzed.easyget.infrastructure.config.redis;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.concurrent.TimeUnit;

/**
 * redis相关操作类
 *
 * @author guichang
 * @date 2018/5/22
 */
public class RedisService {

    @Autowired
    @Qualifier("redisTemplateNew")
    private RedisTemplate redisTemplateNew;

    @Autowired
    @Qualifier("redisTemplateOld")
    private RedisTemplate redisTemplateOld;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setCache(String key, Object value, Long seconds) {
        redisTemplateNew.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setCache(String key, Object value) {
        redisTemplateNew.opsForValue().set(key, value);
    }

    public Boolean setIfAbsent(String key, Object value) {
        return redisTemplateNew.opsForValue().setIfAbsent(key, value);
    }

    public <T> T getCache(String key) {
        try {
            return (T) redisTemplateNew.opsForValue().get(key);
        } catch (SerializationException ex) {
            if (ex.getMessage().indexOf("Unrecognized token") > -1) {
                // 兼容旧字符串数据
                return (T) stringRedisTemplate.opsForValue().get(key);
            } else {
                // 兼容旧对象
                return (T) redisTemplateOld.opsForValue().get(key);
            }
        }
    }

    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplateNew.expire(key, timeout, unit);
    }

    public void clearCache(String key) {
        redisTemplateNew.delete(key);
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
