package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.repository.SystemOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Service
public class SysOptionService {
    @Autowired
    private SystemOptionRepository systemOptionRepository;

    /**
     * 根据key拿到t_system_options表中的value值
     * @return
     */
   /* @Cache(expire=60 * 60 * 24 * 5, key="'system-options:'+#args[0]")
    public <T> T getSystemOptionValueByKey(String key, Class<T> clazz) {
        SystemOption systemOption = systemOptionRepository.findByKey(key.toLowerCase());
        return systemOption == null ? null : (T)systemOption.getValue();
    }

    @Cache(expire=60 * 60 * 24 * 5, key="'system-options:'+#args[0]")
    public String getSystemOptionValueByKey(String key) {
        return getSystemOptionValueByKey(key, String.class);
    }*/
}