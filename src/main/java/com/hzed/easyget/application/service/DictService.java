package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.DictRequest;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.repository.AuthItemRepository;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.Dict;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guichang
 * @date 2018/5/22
 */
@Service
@Slf4j
public class DictService {

    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AuthItemRepository authItemRepository;

    public Dict getDictByCode(String moduleCode) {
        String Dict = redisService.getCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode);

        return null;
    }

    public List<DictResponse> getDictByModule(DictRequest request) {
        List<DictResponse> dictResponseList = Lists.newArrayList();
        String moduleCode = request.getModuleCode();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        //获取缓存数据,缓存没有，才查询数据库
        String Dict = redisService.getCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode);
        if (Dict == null) {

            List<Dict> dictList = dictRepository.findByModuleCodeAndLanguageWithExp(moduleCode,i18n);
            dictList.forEach(dict -> {
                AuthItem authItem = authItemRepository.findByCode(dict.getDicCode());
                DictResponse dictResponse = new DictResponse();
                dictResponse.setDicName(dict.getDicName());
                dictResponse.setIsUse(authItem.getIsUse());
                dictResponseList.add(dictResponse);
            });

        }

        //放入缓存5小时
     //   redisService.setCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode , dictResponseList,5* 3600L);

        return dictResponseList;
    }

    public void clearCache(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }

        if ("all".equals(key)) {

        } else {

        }
    }
}
