package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.BillLedger;
import com.hzed.easyget.persistence.auto.entity.example.BillLedgerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillLedgerMapper {
    long countByExample(BillLedgerExample example);

    int deleteByExample(BillLedgerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BillLedger record);

    int insertSelective(BillLedger record);

    List<BillLedger> selectByExample(BillLedgerExample example);

    List<BillLedger> selectByExampleSelective(@Param("example") BillLedgerExample example, @Param("selective") BillLedger.Column ... selective);

    BillLedger selectByPrimaryKey(Long id);

    BillLedger selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") BillLedger.Column ... selective);

    int updateByExampleSelective(@Param("record") BillLedger record, @Param("example") BillLedgerExample example);

    int updateByExample(@Param("record") BillLedger record, @Param("example") BillLedgerExample example);

    int updateByPrimaryKeySelective(BillLedger record);

    int updateByPrimaryKey(BillLedger record);

    BillLedger selectOneByExample(BillLedgerExample example);

    BillLedger selectOneByExampleSelective(@Param("example") BillLedgerExample example, @Param("selective") BillLedger.Column ... selective);

    int batchInsert(@Param("list") List<BillLedger> list);

    int batchInsertSelective(@Param("list") List<BillLedger> list, @Param("selective") BillLedger.Column ... selective);
}