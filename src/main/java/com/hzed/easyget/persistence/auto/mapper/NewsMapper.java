package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.News;
import com.hzed.easyget.persistence.auto.entity.example.NewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    long countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExample(NewsExample example);

    List<News> selectByExampleSelective(@Param("example") NewsExample example, @Param("selective") News.Column ... selective);

    News selectByPrimaryKey(Long id);

    News selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") News.Column ... selective);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    News selectOneByExample(NewsExample example);

    News selectOneByExampleSelective(@Param("example") NewsExample example, @Param("selective") News.Column ... selective);

    News selectOneByExampleWithBLOBs(NewsExample example);

    int batchInsert(@Param("list") List<News> list);

    int batchInsertSelective(@Param("list") List<News> list, @Param("selective") News.Column ... selective);
}