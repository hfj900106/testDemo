package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.application.enums.StatusEnum;
import com.hzed.easyget.persistence.auto.entity.Bomb;
import com.hzed.easyget.persistence.auto.entity.example.BombExample;
import com.hzed.easyget.persistence.auto.mapper.BombMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wuchengwu
 * @date 2018/5/25
 */
@Repository
public class BombRepository {

    @Autowired
    private BombMapper bombMapper;
    public List<Bomb> getBombList() {
        BombExample example = new BombExample();
        example.setOrderByClause("up_time desc");
        example.createCriteria()
                .andIsUseEqualTo(StatusEnum.ENABLE.getCode());
        return bombMapper.selectByExample(example);
    }
}