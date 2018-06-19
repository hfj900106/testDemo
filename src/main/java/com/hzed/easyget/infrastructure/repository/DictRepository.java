package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.example.DictExample;
import com.hzed.easyget.persistence.auto.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Dict findByCodeWithExp(String code) {
        DictExample example = new DictExample();
        example.createCriteria().andDicCodeEqualTo(code);

        Dict dict = dictMapper.selectOneByExample(example);

        if (dict == null) {
            throw new ComBizException(BizCodeEnum.DICT_NOTEXISTS);
        }
        return dict;
    }

    public List<Dict> findByModuleCodeWithExp(String moduleCode) {
        DictExample example = new DictExample();
        example.setOrderByClause("orderby asc");
        example.createCriteria().andModuleCodeEqualTo(moduleCode);

        List<Dict> dicts = dictMapper.selectByExample(example);

        if (dicts == null || dicts.isEmpty()) {
            throw new ComBizException(BizCodeEnum.DICT_NOTEXISTS);
        }
        return dicts;

    }

    public List<Dict> findByModuleCodeAndLanguageWithExp(String moduleCode,String language) {
        DictExample example = new DictExample();
        example.setOrderByClause("orderby asc");
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language);

        List<Dict> dicts = dictMapper.selectByExample(example);

        if (dicts == null || dicts.isEmpty()) {
            throw new ComBizException(BizCodeEnum.DICT_NOTEXISTS);
        }
        return dicts;

    }

}