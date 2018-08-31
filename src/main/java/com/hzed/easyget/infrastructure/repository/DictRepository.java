package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.example.DictExample;
import com.hzed.easyget.persistence.auto.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

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
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode);

        List<Dict> dicts = dictMapper.selectByExample(example);
        if (ObjectUtils.isEmpty(dicts)) {
            throw new ComBizException(BizCodeEnum.DICT_NOTEXISTS);
        }
        return dicts;
    }

    public List<Dict> findEnableByModuleCodeAndLanguage(String moduleCode, String language) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language).andDicLabelEqualTo("1");

        return dictMapper.selectByExample(example);
    }

    public List<Dict> findByModuleCodeAndLanguage(String moduleCode, String language) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode).andLanguageEqualTo(language);

        return dictMapper.selectByExample(example);
    }

    public List<Dict> findByDicCodeAndLanguage(String dicCode, String language) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andDicCodeEqualTo(dicCode).andLanguageEqualTo(language);
        return dictMapper.selectByExample(example);
    }



    public List<Dict> findByModuleCode(String moduleCode) {
        DictExample example = new DictExample();
        example.setOrderByClause(Dict.Column.orderby.asc());
        example.createCriteria().andModuleCodeEqualTo(moduleCode);

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