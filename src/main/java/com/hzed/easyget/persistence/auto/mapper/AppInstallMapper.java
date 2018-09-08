package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.AppInstall;
import com.hzed.easyget.persistence.auto.entity.example.AppInstallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInstallMapper {
    long countByExample(AppInstallExample example);

    int deleteByExample(AppInstallExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppInstall record);

    int insertSelective(AppInstall record);

    List<AppInstall> selectByExample(AppInstallExample example);

    List<AppInstall> selectByExampleSelective(@Param("example") AppInstallExample example, @Param("selective") AppInstall.Column ... selective);

    AppInstall selectByPrimaryKey(Long id);

    AppInstall selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AppInstall.Column ... selective);

    int updateByExampleSelective(@Param("record") AppInstall record, @Param("example") AppInstallExample example);

    int updateByExample(@Param("record") AppInstall record, @Param("example") AppInstallExample example);

    int updateByPrimaryKeySelective(AppInstall record);

    int updateByPrimaryKey(AppInstall record);

    AppInstall selectOneByExample(AppInstallExample example);

    AppInstall selectOneByExampleSelective(@Param("example") AppInstallExample example, @Param("selective") AppInstall.Column ... selective);

    int batchInsert(@Param("list") List<AppInstall> list);

    int batchInsertSelective(@Param("list") List<AppInstall> list, @Param("selective") AppInstall.Column ... selective);
}