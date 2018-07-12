package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.IDAreaRequest;
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

    private final String dictKey = RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT;

    public Dict getDictByCode(String dictCode) {

        Dict dict = redisService.getObjCache(dictKey + dictCode);
        if (dict == null) {
            dict = dictRepository.findByCode(dictCode);
            redisService.setObjCache(dictKey + dictCode, dict, 5 * 3600L);
        }
        return dict;
    }

    public List<DictResponse> getDictByModuleCodeAndLanguage(String moduleCode, String i18n) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + moduleCode + RedisConsts.SPLIT + i18n);
        if (!ObjectUtils.isEmpty(dictResponseListCache)) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByModuleCodeAndLanguage(moduleCode, i18n);
        List<DictResponse> dictResponseList = addDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + moduleCode + RedisConsts.SPLIT + i18n, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    public List<DictResponse> getDictByDicCodeAndLanguage(String dicCode, String local) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + dicCode + RedisConsts.SPLIT + local);
        if (!ObjectUtils.isEmpty(dictResponseListCache)) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByDicCodeAndLanguage(dicCode, local);
        List<DictResponse> dictResponseList = addDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + dicCode + RedisConsts.SPLIT + local, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    /**
     * 不根据国际化查询的字典列表
     * @param moduleCode
     * @return
     */
    public List<DictResponse> getDictByModule(String moduleCode) {
        // 获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + moduleCode);
        if (!ObjectUtils.isEmpty(dictResponseListCache)) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByModuleCode(moduleCode);
        List<DictResponse> dictResponseList = addDictResponseList(dictList);

        // 放入缓存5小时
        redisService.setObjCache(dictKey + moduleCode, dictResponseList, 5 * 3600L);

        return dictResponseList;
    }

    private List<DictResponse> addDictResponseList(List<Dict> dictList) {
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


    public List<IDAreaResponse> getIDAreaList(IDAreaRequest request) {
        List<IDAreaResponse> idAreaResponseList = Lists.newArrayList();
        String parent = request.getParent();

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

    public void clearModuleCache(String module) {
        Dict dict = redisService.getObjCache(dictKey + module);
        if (ObjectUtils.isEmpty(dict)) {
            log.info("{}的缓存为空", module);
            return;
        }
        log.info("开始清理缓存，缓存的module:{}", module);
        redisService.clearCache(dictKey + module);
        log.info("清理缓存完毕。。。");

    }

    public void clearModuleAndI18nCache(String module, String i18n) {

        List<DictResponse> dictResponseListCache = redisService.getObjCache(dictKey + module + RedisConsts.SPLIT + i18n);
        if (ObjectUtils.isEmpty(dictResponseListCache)) {
            log.info("{}的缓存为空", module);
            return;
        }
        log.info("开始清理缓存，缓存的module:{}，i18n：{}", module, i18n);
        redisService.clearCache(dictKey + module + RedisConsts.SPLIT + i18n);
        log.info("清理缓存完毕。。。");

    }
}
