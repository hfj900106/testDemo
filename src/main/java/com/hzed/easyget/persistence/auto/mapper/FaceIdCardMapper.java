package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.FaceIdCard;
import com.hzed.easyget.persistence.auto.entity.example.FaceIdCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceIdCardMapper {
    long countByExample(FaceIdCardExample example);

    int deleteByExample(FaceIdCardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FaceIdCard record);

    int insertSelective(FaceIdCard record);

    List<FaceIdCard> selectByExample(FaceIdCardExample example);

    List<FaceIdCard> selectByExampleSelective(@Param("example") FaceIdCardExample example, @Param("selective") FaceIdCard.Column ... selective);

    FaceIdCard selectByPrimaryKey(Long id);

    FaceIdCard selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") FaceIdCard.Column ... selective);

    int updateByExampleSelective(@Param("record") FaceIdCard record, @Param("example") FaceIdCardExample example);

    int updateByExample(@Param("record") FaceIdCard record, @Param("example") FaceIdCardExample example);

    int updateByPrimaryKeySelective(FaceIdCard record);

    int updateByPrimaryKey(FaceIdCard record);

    FaceIdCard selectOneByExample(FaceIdCardExample example);

    FaceIdCard selectOneByExampleSelective(@Param("example") FaceIdCardExample example, @Param("selective") FaceIdCard.Column ... selective);

    int batchInsert(@Param("list") List<FaceIdCard> list);

    int batchInsertSelective(@Param("list") List<FaceIdCard> list, @Param("selective") FaceIdCard.Column ... selective);
}