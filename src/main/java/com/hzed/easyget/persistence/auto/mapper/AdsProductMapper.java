package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.AdsProduct;
import com.hzed.easyget.persistence.auto.entity.example.AdsProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdsProductMapper {
    long countByExample(AdsProductExample example);

    int deleteByExample(AdsProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdsProduct record);

    int insertSelective(AdsProduct record);

    List<AdsProduct> selectByExample(AdsProductExample example);

    List<AdsProduct> selectByExampleSelective(@Param("example") AdsProductExample example, @Param("selective") AdsProduct.Column ... selective);

    AdsProduct selectByPrimaryKey(Long id);

    AdsProduct selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AdsProduct.Column ... selective);

    int updateByExampleSelective(@Param("record") AdsProduct record, @Param("example") AdsProductExample example);

    int updateByExample(@Param("record") AdsProduct record, @Param("example") AdsProductExample example);

    int updateByPrimaryKeySelective(AdsProduct record);

    int updateByPrimaryKey(AdsProduct record);

    AdsProduct selectOneByExample(AdsProductExample example);

    AdsProduct selectOneByExampleSelective(@Param("example") AdsProductExample example, @Param("selective") AdsProduct.Column ... selective);

    int batchInsert(@Param("list") List<AdsProduct> list);

    int batchInsertSelective(@Param("list") List<AdsProduct> list, @Param("selective") AdsProduct.Column ... selective);
}