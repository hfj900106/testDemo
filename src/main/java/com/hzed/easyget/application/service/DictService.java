package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.persistence.auto.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询字典表接口
 *
 * @author wuchengwu
 * @date 2018/5/22
 */
@Service
public class DictService {

    @Autowired
    private DictRepository dictRepository;

    public Dict getAppVersionByDicCode(String dicCode) {

        Dict dict = dictRepository.getVersionByDicCode(dicCode);
        return dict;
    }

}