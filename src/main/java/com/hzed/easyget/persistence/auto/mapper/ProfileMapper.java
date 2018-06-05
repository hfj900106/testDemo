package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Profile;
import com.hzed.easyget.persistence.auto.entity.example.ProfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfileMapper {
    long countByExample(ProfileExample example);

    int deleteByExample(ProfileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Profile record);

    int insertSelective(Profile record);

    List<Profile> selectByExample(ProfileExample example);

    List<Profile> selectByExampleSelective(@Param("example") ProfileExample example, @Param("selective") Profile.Column ... selective);

    Profile selectByPrimaryKey(Long id);

    Profile selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Profile.Column ... selective);

    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);

    Profile selectOneByExample(ProfileExample example);

    Profile selectOneByExampleSelective(@Param("example") ProfileExample example, @Param("selective") Profile.Column ... selective);

    int batchInsert(@Param("list") List<Profile> list);

    int batchInsertSelective(@Param("list") List<Profile> list, @Param("selective") Profile.Column ... selective);
}