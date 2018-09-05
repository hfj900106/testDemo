package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.IDAreaResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.AppVersionModel;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.IDAreaRepository;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.IDArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
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
        String key = dictKey + dictCode;
        Dict dict = redisService.getCache(key);
        if (dict != null) {
            return dict;
        }
        dict = dictRepository.findOneByCodeWithExp(dictCode);
        redisService.setCache(key, dict);
        return dict;
    }

    public Dict getDictByCodeAndLanguage(String dicCode, String language) {
        String key = dictKey + dicCode + RedisConsts.SPLIT + language;
        Dict dict = redisService.getCache(key);
        if (dict != null) {
            return dict;
        }
        dict = dictRepository.findOneByCodeAndLanguage(dicCode, language);
        redisService.setCache(key, dict);
        return dict;
    }

    public List<DictResponse> getDictListByCode(String dictCode) {
        String key = dictKey + dictCode + "list";
        List<DictResponse> respList = redisService.getCache(key);
        if (respList != null) {
            return respList;
        }
        respList = buildDictResponseList(dictRepository.findListByCode(dictCode));
        redisService.setCache(key, respList);
        return respList;
    }

    public List<DictResponse> getDictByModuleCodeAndLanguage(String moduleCode, String language) {
        String key = dictKey + moduleCode + RedisConsts.SPLIT + language;
        List<DictResponse> respList = redisService.getCache(key);
        if (respList != null) {
            return respList;
        }
        respList = buildDictResponseList(dictRepository.findByModuleCodeAndLanguage(moduleCode, language));
        redisService.setCache(key, respList);
        return respList;
    }

    /**
     * 不根据国际化查询的字典列表
     */
    public List<DictResponse> getDictByModuleCode(String moduleCode) {
        String key = dictKey + moduleCode;
        List<DictResponse> respList = redisService.getCache(key);
        if (respList != null) {
            return respList;
        }
        respList = buildDictResponseList(dictRepository.findByModuleCode(moduleCode));
        redisService.setCache(key, respList);
        return respList;
    }

    /**
     * 获取下级地市列表
     */
    public List<IDAreaResponse> getIDAreaList(String parent) {
        String key = dictKey + parent;
        List<IDAreaResponse> respList = redisService.getCache(key);
        if (respList != null) {
            return respList;
        }
        List<IDArea> idAreaList = idAreaRepository.findByParent(parent);
        idAreaList.forEach(idArea -> {
            IDAreaResponse idAreaResponse = new IDAreaResponse();
            idAreaResponse.setName(idArea.getName());
            respList.add(idAreaResponse);
        });
        redisService.setCache(key, respList);
        return respList;
    }

    private List<DictResponse> buildDictResponseList(List<Dict> dictList) {
        List<DictResponse> respList = Lists.newArrayList();
        dictList.forEach(dict -> {
            DictResponse dictResponse = new DictResponse();
            dictResponse.setDictCode(dict.getDicCode());
            dictResponse.setDictValue(dict.getDicValue());
            dictResponse.setDictName(dict.getDicName());
            respList.add(dictResponse);
        });
        return respList;
    }

    public void clearCodeCache(String code) {
        String key = dictKey + code;
        Object obj = redisService.getCache(key);
        if (obj == null) {
            log.info("key：{} 无缓存，不做清理操作", key);
        } else {
            log.info("开始清理缓存，{}：{}", key, JSON.toJSONString(obj));
            redisService.clearCache(key);
        }

        String key2 = dictKey + code + "list";
        Object obj2 = redisService.getCache(key2);
        if (obj2 == null) {
            log.info("key2：{} 无缓存，不做清理操作", key2);
        } else {
            log.info("开始清理缓存，{}：{}", key2, JSON.toJSONString(obj2));
            redisService.clearCache(key2);
        }

        redisService.clearCache(key);
        log.info("清理缓存完毕");
    }

    public void clearCodeAndLanguageCache(String code, String language) {
        String key = dictKey + code + RedisConsts.SPLIT + language;
        Object obj = redisService.getCache(key);
        if (ObjectUtils.isEmpty(obj)) {
            log.info("code：{} 无缓存，不做清理操作", code);
            return;
        }
        log.info("开始清理缓存，{}，{}：{}", code, language, JSON.toJSONString(obj));
        redisService.clearCache(key);
        log.info("清理缓存完毕");
    }

    public void switchSmsChannel(String channel) {
        channel = channel.toUpperCase();
        if (!channel.equals(ComConsts.CM) && !channel.equals(ComConsts.NX)) {
            throw new NestedException(BizCodeEnum.SERVICE_EXCEPTION, "无此短信渠道");
        }

        Dict smsDict = dictRepository.findOneByCodeWithExp(ComConsts.SMS_DICT_CODE);
        log.info("当前的短信通道：{}", smsDict.getDicValue());
        log.info("即将切换成渠道：{}", channel);
        Dict dictUpdate = Dict.builder().id(smsDict.getId()).dicValue(channel).updateTime(LocalDateTime.now()).build();
        dictRepository.update(dictUpdate);
        // 清理缓存数据
        clearCodeCache(ComConsts.SMS_DICT_CODE);
        log.info("渠道切换成功");
    }

    public void updateVersion(String channel, String newVersion, String minVersionCode) {
        Dict versionDict = dictRepository.findOneByCode(channel);
        if (ObjectUtils.isEmpty(versionDict)) {
            throw new NestedException(BizCodeEnum.SERVICE_EXCEPTION, "无此应用商店");
        }
        AppVersionModel appVersionModel = JSONObject.parseObject(versionDict.getDicLabel(), AppVersionModel.class);
        log.info("当前版本信息，version：{}，minVersionCode：{}", versionDict.getDicValue(), appVersionModel.getMinimum_version());
        appVersionModel.setMinimum_version(Integer.parseInt(minVersionCode));
        log.info("即将更新成，version：{}，minVersionCode：{}", newVersion, minVersionCode);
        Dict dictUpdate = Dict.builder().id(versionDict.getId()).dicValue(newVersion).dicLabel(JSONObject.toJSONString(appVersionModel)).updateTime(LocalDateTime.now()).build();
        dictRepository.update(dictUpdate);
        // 清理缓存
        clearCodeCache(channel);
        log.info("版本更新成功");
    }


}
