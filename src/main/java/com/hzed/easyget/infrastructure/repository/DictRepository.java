package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.example.DictExample;
import com.hzed.easyget.persistence.auto.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wuchengwu
 * @date 2018/5/22
 */
@Repository
public class DictRepository {

    @Autowired
    private DictMapper dictMapper;

    public Dict getVersionByDicCode(String dicCode) {
        DictExample example = new DictExample();
        example.createCriteria().andDicCodeEqualTo(dicCode);
        return dictMapper.selectOneByExample(example);
    }
}