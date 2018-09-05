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

    public List<Dict> findListByCode(String code) {
        DictExample example = new DictExample();
        example.createCriteria().andDicCodeEqualTo(code);
        return dictMapper.selectByExample(example);
    }

    public Dict findOneByCode(String code) {
        DictExample example = new DictExample();
        example.createCriteria().andDicCodeEqualTo(code);
        return dictMapper.selectOneByExample(example);
    }

    public Dict findOneByCodeWithExp(String code) {
        Dict dict = findOneByCode(code);
        if (dict == null) {
            throw new ComBizException(BizCodeEnum.DICT_NOTEXISTS);
        }
        return dict;
    }

    public List<Dict> findByModuleCode(String moduleCode) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode);
        return dictMapper.selectByExample(example);
    }

    public List<Dict> findEnableByModuleCodeAndLanguage(String moduleCode, String language) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language).andDicLabelEqualTo("1");
        return dictMapper.selectByExample(example);
    }

    public List<Dict> findGroupByModuleCodeAndLanguage(String moduleCode, String language, String remark1, String remark2) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language).andRemarkBetween(remark1, remark2);
        return dictMapper.selectByExample(example);
    }

    public List<Dict> findByModuleCodeAndLanguage(String moduleCode, String language) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language);
        return dictMapper.selectByExample(example);
    }

    public Dict findOneByCodeAndLanguage(String dicCode, String language) {
        DictExample example = new DictExample();
        example.createCriteria().andDicCodeEqualTo(dicCode).andLanguageEqualTo(language);
        return dictMapper.selectOneByExample(example);
    }

    public void update(Dict dict) {
        dictMapper.updateByPrimaryKeySelective(dict);
    }
}