package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.FaceIdcardAuth;
import com.hzed.easyget.persistence.auto.entity.example.FaceIdcardAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceIdcardAuthMapper {
    long countByExample(FaceIdcardAuthExample example);

    int deleteByExample(FaceIdcardAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FaceIdcardAuth record);

    int insertSelective(FaceIdcardAuth record);

    List<FaceIdcardAuth> selectByExample(FaceIdcardAuthExample example);

    List<FaceIdcardAuth> selectByExampleSelective(@Param("example") FaceIdcardAuthExample example, @Param("selective") FaceIdcardAuth.Column ... selective);

    FaceIdcardAuth selectByPrimaryKey(Long id);

    FaceIdcardAuth selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") FaceIdcardAuth.Column ... selective);

    int updateByExampleSelective(@Param("record") FaceIdcardAuth record, @Param("example") FaceIdcardAuthExample example);

    int updateByExample(@Param("record") FaceIdcardAuth record, @Param("example") FaceIdcardAuthExample example);

    int updateByPrimaryKeySelective(FaceIdcardAuth record);

    int updateByPrimaryKey(FaceIdcardAuth record);

    FaceIdcardAuth selectOneByExample(FaceIdcardAuthExample example);

    FaceIdcardAuth selectOneByExampleSelective(@Param("example") FaceIdcardAuthExample example, @Param("selective") FaceIdcardAuth.Column ... selective);

    int batchInsert(@Param("list") List<FaceIdcardAuth> list);

    int batchInsertSelective(@Param("list") List<FaceIdcardAuth> list, @Param("selective") FaceIdcardAuth.Column ... selective);
}