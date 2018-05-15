package com.demo.hzed.persistence.auto.base;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, E, PK extends Serializable> {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExampleWithBLOBsWithRowbounds(E example, RowBounds rowBounds);

    List<T> selectByExampleWithBLOBs(E example);

    List<T> selectByExampleWithRowbounds(E example, RowBounds rowBounds);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(PK id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExampleWithBLOBs(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);
}