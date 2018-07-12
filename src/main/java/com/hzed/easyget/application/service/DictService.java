package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.IDAreaResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.IDAreaRepository;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.IDArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private IDAreaRepository idAreaRepository;

    private final String dictKey = RedisConsts.DICT_CODE + RedisConsts.SPLIT;

    public Dict getDictByCode(String dictCode) {
        Dict dict = redisService.getObjCache(dictKey + dictCode);
        if (dict == null) {
            dict = dictRepository.findByCode(dictCode);
            redisService.setObjCache(dictKey + dictCode, dict, 5 * 3600L);
        }
        return dict;
    }

    public List<DictResponse> getDictByModuleCodeAndLanguage(String moduleCode, String language) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> respList = redisService.getObjCache(dictKey + moduleCode + RedisConsts.SPLIT + language);
        if (!ObjectUtils.isEmpty(respList)) {
            return respList;
        }

        List<Dict> dictList = dictRepository.findByModuleCodeAndLanguage(moduleCode, language);
        List<DictResponse> dictResponseList = buildDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + moduleCode + RedisConsts.SPLIT + language, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    public List<DictResponse> getDictByDicCodeAndLanguage(String dicCode, String language) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + dicCode + RedisConsts.SPLIT + language);
        if (!ObjectUtils.isEmpty(dictResponseListCache)) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByDicCodeAndLanguage(dicCode, language);
        List<DictResponse> dictResponseList = buildDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + dicCode + RedisConsts.SPLIT + language, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    /**
     * 不根据国际化查询的字典列表
     */
    public List<DictResponse> getDictByModuleCode(String moduleCode) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + moduleCode);
        if (!ObjectUtils.isEmpty(dictResponseListCache)) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByModuleCode(moduleCode);
        List<DictResponse> dictResponseList = buildDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + moduleCode, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    private List<DictResponse> buildDictResponseList(List<Dict> dictList) {
        List<DictResponse> dictResponseList = Lists.newArrayList();
        dictList.forEach(dict -> {
            DictResponse dictResponse = new DictResponse();
            dictResponse.setDictCode(dict.getDicCode());
            dictResponse.setDictValue(dict.getDicValue());
            dictResponse.setDictName(dict.getDicName());
            dictResponseList.add(dictResponse);
        });
        return dictResponseList;
    }

    public List<IDAreaResponse> getIDAreaList(String parent) {
        List<IDAreaResponse> idAreaResponseList = Lists.newArrayList();

        List<IDAreaResponse> idAreaResponseCache = redisService.getObjCache(dictKey + parent);
        if (idAreaResponseCache != null) {
            return idAreaResponseCache;
        }

        List<IDArea> idAreaList = idAreaRepository.findByParent(parent);
        idAreaList.forEach(idArea -> {
            IDAreaResponse idAreaResponse = new IDAreaResponse();
            idAreaResponse.setName(idArea.getName());
            idAreaResponseList.add(idAreaResponse);
        });
        redisService.setObjCache(dictKey + parent, idAreaResponseList, 5 * 3600L);
        return idAreaResponseList;

    }

    public void clearCodeCache(String code) {
        Dict dict = redisService.getObjCache(dictKey + code);
        if (ObjectUtils.isEmpty(dict)) {
            log.info("code：{} 的无缓存，不做清理操作", code);
            return;
        }
        log.info("开始清理缓存，{}：{}", code, JSON.toJSONString(dict));
        redisService.clearCache(dictKey + code);
        log.info("清理缓存完毕");
    }

    public void clearCodeAndLanguageCache(String code, String language) {
        List<DictResponse> respList = redisService.getObjCache(dictKey + code + RedisConsts.SPLIT + language);
        if (ObjectUtils.isEmpty(respList)) {
            log.info("code：{} 的无缓存，不做清理操作", code);
            return;
        }
        log.info("开始清理缓存，{},{}：{}", code, language, JSON.toJSONString(respList));
        redisService.clearCache(dictKey + code + RedisConsts.SPLIT + language);
        log.info("清理缓存完毕");

    }
}
