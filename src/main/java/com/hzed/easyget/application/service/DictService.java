package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.DictRequest;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.IDAreaRequest;
import com.hzed.easyget.controller.model.IDAreaResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.repository.AuthItemRepository;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.IDAreaRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.IDArea;
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
    @Autowired
    private IDAreaRepository idAreaRepository;

    public Dict getDictByCode(String moduleCode) {

        Dict dict = redisService.getObjCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode);
        if (dict == null) {
            dict = dictRepository.findByCode(moduleCode);
        }

        redisService.setObjCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode, dict, 5 * 3600L);

        return dict;
    }

    public List<DictResponse> getDictByModule(DictRequest request) {
        List<DictResponse> dictResponseList = Lists.newArrayList();
        String moduleCode = request.getModuleCode();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        //获取缓存数据,缓存没有，才查询数据库
        List<DictResponse> dictResponseListCache = redisService.getObjCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode + RedisConsts.SPLIT + i18n);
        if (dictResponseListCache !=null) {
            return dictResponseListCache;
        }

        List<Dict> dictList = dictRepository.findByModuleCodeAndLanguage(moduleCode, i18n);
        dictList.forEach(dict -> {
            AuthItem authItem = authItemRepository.findByCode(dict.getDicCode());
            DictResponse dictResponse = new DictResponse();
            if (authItem != null) {
                dictResponse.setDictCode(dict.getDicCode());
                dictResponse.setDictValue(dict.getDicValue());

            }
            dictResponseList.add(dictResponse);
        });

        //放入缓存5小时
        redisService.setObjCache(RedisConsts.DICT_MODULE_CODE + RedisConsts.SPLIT + moduleCode + RedisConsts.SPLIT + i18n, dictResponseList, 5 * 3600L);

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

    public List<IDAreaResponse> getIDAreaList(IDAreaRequest request) {
        List<IDAreaResponse> idAreaResponseList = Lists.newArrayList();
        String parent = request.getParent();

        List<IDAreaResponse> idAreaResponseCache = redisService.getObjCache(RedisConsts.ID_PARENT_NAME + RedisConsts.SPLIT + parent);
        if (idAreaResponseCache != null) {
            return idAreaResponseCache;
        }

        List<IDArea> idAreaList = idAreaRepository.findByParent(parent);
        idAreaList.forEach(idArea -> {
            IDAreaResponse idAreaResponse = new IDAreaResponse();
            idAreaResponse.setName(idArea.getName());
            idAreaResponseList.add(idAreaResponse);
        });
        redisService.setObjCache(RedisConsts.ID_PARENT_NAME + RedisConsts.SPLIT + parent, idAreaResponseList, 5 * 3600L);
        return idAreaResponseList;

    }
}
