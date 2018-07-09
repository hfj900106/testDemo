package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.IDArea;
import com.hzed.easyget.persistence.auto.entity.example.IDAreaExample;
import com.hzed.easyget.persistence.auto.mapper.IDAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地区字典表
 *
 * @author wuchengwu
 * @date 2018/6/20
 */
@Repository
public class IDAreaRepository {

    @Autowired
    private IDAreaMapper idAreaMapper;

    public List<IDArea> findByParent(String parent) {
        IDAreaExample example = new IDAreaExample();
        example.createCriteria().andParentEqualTo(parent);
        example.setOrderByClause(IDArea.Column.weight.asc() + "," + IDArea.Column.name.asc());
        return idAreaMapper.selectByExample(example);
    }
}