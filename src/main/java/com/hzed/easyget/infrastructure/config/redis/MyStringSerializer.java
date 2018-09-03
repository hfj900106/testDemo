package com.hzed.easyget.infrastructure.config.redis;

import com.hzed.easyget.infrastructure.consts.RedisConsts;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;

/**
 * 自定义redis key序列化
 *
 * @author guichang
 * @date 2018/9/3
 */

public class MyStringSerializer implements RedisSerializer<String> {

    private String prefix;
    private final Charset charset;

    public MyStringSerializer(String prefix) {
        this(Charset.forName("UTF8"), prefix);
    }

    public MyStringSerializer(Charset charset, String prefix) {
        this.charset = charset;
        // 加分割符
        if(!prefix.endsWith(RedisConsts.SPLIT)) {
            this.prefix = prefix + RedisConsts.SPLIT;
        }
    }

    @Override
    public String deserialize(byte[] bytes) {
        String saveKey = new String(bytes, charset);
        int indexOf = saveKey.indexOf(prefix);
        if (indexOf > 0) {
            saveKey = saveKey.substring(indexOf);
        }
        return (saveKey.getBytes() == null ? null : saveKey);
    }

    @Override
    public byte[] serialize(String string) {
        String key = prefix + string;
        return (key == null ? null : key.getBytes(charset));
    }
}