package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.example.RepayInfoFlowJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepayInfoFlowJobMapper {
    long countByExample(RepayInfoFlowJobExample example);

    int deleteByExample(RepayInfoFlowJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RepayInfoFlowJob record);

    int insertSelective(RepayInfoFlowJob record);

    List<RepayInfoFlowJob> selectByExample(RepayInfoFlowJobExample example);

    List<RepayInfoFlowJob> selectByExampleSelective(@Param("example") RepayInfoFlowJobExample example, @Param("selective") RepayInfoFlowJob.Column ... selective);

    RepayInfoFlowJob selectByPrimaryKey(Long id);

    RepayInfoFlowJob selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RepayInfoFlowJob.Column ... selective);

    int updateByExampleSelective(@Param("record") RepayInfoFlowJob record, @Param("example") RepayInfoFlowJobExample example);

    int updateByExample(@Param("record") RepayInfoFlowJob record, @Param("example") RepayInfoFlowJobExample example);

    int updateByPrimaryKeySelective(RepayInfoFlowJob record);

    int updateByPrimaryKey(RepayInfoFlowJob record);

    RepayInfoFlowJob selectOneByExample(RepayInfoFlowJobExample example);

    RepayInfoFlowJob selectOneByExampleSelective(@Param("example") RepayInfoFlowJobExample example, @Param("selective") RepayInfoFlowJob.Column ... selective);

    int batchInsert(@Param("list") List<RepayInfoFlowJob> list);

    int batchInsertSelective(@Param("list") List<RepayInfoFlowJob> list, @Param("selective") RepayInfoFlowJob.Column ... selective);
}