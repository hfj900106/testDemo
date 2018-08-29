package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.BidDetailFee;
import com.hzed.easyget.persistence.auto.entity.example.BidDetailFeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BidDetailFeeMapper {
    long countByExample(BidDetailFeeExample example);

    int deleteByExample(BidDetailFeeExample example);

    int insert(BidDetailFee record);

    int insertSelective(BidDetailFee record);

    List<BidDetailFee> selectByExample(BidDetailFeeExample example);

    List<BidDetailFee> selectByExampleSelective(@Param("example") BidDetailFeeExample example, @Param("selective") BidDetailFee.Column ... selective);

    int updateByExampleSelective(@Param("record") BidDetailFee record, @Param("example") BidDetailFeeExample example);

    int updateByExample(@Param("record") BidDetailFee record, @Param("example") BidDetailFeeExample example);

    BidDetailFee selectOneByExample(BidDetailFeeExample example);

    BidDetailFee selectOneByExampleSelective(@Param("example") BidDetailFeeExample example, @Param("selective") BidDetailFee.Column ... selective);

    BidDetailFee selectByPrimaryKeySelective(@Param("selective") BidDetailFee.Column ... selective);

    int batchInsert(@Param("list") List<BidDetailFee> list);

    int batchInsertSelective(@Param("list") List<BidDetailFee> list, @Param("selective") BidDetailFee.Column ... selective);
}