package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Bomb;
import com.hzed.easyget.persistence.auto.entity.example.BombExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BombMapper {
    long countByExample(BombExample example);

    int deleteByExample(BombExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bomb record);

    int insertSelective(Bomb record);

    List<Bomb> selectByExample(BombExample example);

    List<Bomb> selectByExampleSelective(@Param("example") BombExample example, @Param("selective") Bomb.Column ... selective);

    Bomb selectByPrimaryKey(Long id);

    Bomb selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Bomb.Column ... selective);

    int updateByExampleSelective(@Param("record") Bomb record, @Param("example") BombExample example);

    int updateByExample(@Param("record") Bomb record, @Param("example") BombExample example);

    int updateByPrimaryKeySelective(Bomb record);

    int updateByPrimaryKey(Bomb record);

    Bomb selectOneByExample(BombExample example);

    Bomb selectOneByExampleSelective(@Param("example") BombExample example, @Param("selective") Bomb.Column ... selective);

    int batchInsert(@Param("list") List<Bomb> list);

    int batchInsertSelective(@Param("list") List<Bomb> list, @Param("selective") Bomb.Column ... selective);
}