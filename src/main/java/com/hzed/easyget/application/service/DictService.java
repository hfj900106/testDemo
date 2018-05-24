package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.persistence.auto.entity.Dict;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guichang
 * @date 2018/5/22
 */
@Service
@Slf4j
public class DictService {

    private static final Cache<String, Dict> CACHE = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(20)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(10)
            //设置cache中的数据在写入之后的存活时间
            .expireAfterWrite(3, TimeUnit.HOURS)
            .expireAfterAccess(3, TimeUnit.HOURS)
            .maximumSize(500)
            .removalListener(notification -> log.info("清除字典缓存，key：{}，cause：{}，value：{}",
                    notification.getKey(), notification.getCause(), JSON.toJSON(notification.getValue())))
            .build();

    @Autowired
    private DictRepository dictRepository;

    private static final Map<String, List<Dict>> MODULE_MAP = Maps.newHashMap();

    public Dict getDictByCode(String code) {
        Dict dict = CACHE.getIfPresent(code);
        if (dict != null) {
            return dict;
        }
        dict = dictRepository.findByCodeWithExp(code);
        CACHE.put(dict.getDicCode(), dict);
        return dict;
    }

    public List<Dict> getDictsByModule(String moduleCode) {
        List<Dict> dicts = MODULE_MAP.get(moduleCode);
        if(dicts == null || dicts.isEmpty()) {
            List<Dict> moduleDicts = dictRepository.findByModuleCodeWithExp(moduleCode);
            MODULE_MAP.put(moduleCode, moduleDicts);
            return moduleDicts;
        }
        return dicts;
    }

    @Deprecated
    public void clearCache(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }

        if ("all".equals(key)) {
            CACHE.invalidateAll();
            // 清除moduleMap
            MODULE_MAP.clear();
            log.info("成功清理所有缓存");
        } else {
            CACHE.invalidate(key);
            MODULE_MAP.remove(key);
            log.info("成功清理dic_code或module_code为{}的缓存", key);
        }
    }
}
