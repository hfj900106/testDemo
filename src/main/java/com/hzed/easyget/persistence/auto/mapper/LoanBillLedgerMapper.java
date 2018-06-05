package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.LoanBillLedger;
import com.hzed.easyget.persistence.auto.entity.example.LoanBillLedgerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanBillLedgerMapper {
    long countByExample(LoanBillLedgerExample example);

    int deleteByExample(LoanBillLedgerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LoanBillLedger record);

    int insertSelective(LoanBillLedger record);

    List<LoanBillLedger> selectByExample(LoanBillLedgerExample example);

    List<LoanBillLedger> selectByExampleSelective(@Param("example") LoanBillLedgerExample example, @Param("selective") LoanBillLedger.Column ... selective);

    LoanBillLedger selectByPrimaryKey(Long id);

    LoanBillLedger selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LoanBillLedger.Column ... selective);

    int updateByExampleSelective(@Param("record") LoanBillLedger record, @Param("example") LoanBillLedgerExample example);

    int updateByExample(@Param("record") LoanBillLedger record, @Param("example") LoanBillLedgerExample example);

    int updateByPrimaryKeySelective(LoanBillLedger record);

    int updateByPrimaryKey(LoanBillLedger record);

    LoanBillLedger selectOneByExample(LoanBillLedgerExample example);

    LoanBillLedger selectOneByExampleSelective(@Param("example") LoanBillLedgerExample example, @Param("selective") LoanBillLedger.Column ... selective);

    int batchInsert(@Param("list") List<LoanBillLedger> list);

    int batchInsertSelective(@Param("list") List<LoanBillLedger> list, @Param("selective") LoanBillLedger.Column ... selective);
}