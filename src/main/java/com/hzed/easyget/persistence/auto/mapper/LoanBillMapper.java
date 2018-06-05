package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.LoanBill;
import com.hzed.easyget.persistence.auto.entity.example.LoanBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoanBillMapper {
    long countByExample(LoanBillExample example);

    int deleteByExample(LoanBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LoanBill record);

    int insertSelective(LoanBill record);

    List<LoanBill> selectByExample(LoanBillExample example);

    List<LoanBill> selectByExampleSelective(@Param("example") LoanBillExample example, @Param("selective") LoanBill.Column ... selective);

    LoanBill selectByPrimaryKey(Long id);

    LoanBill selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LoanBill.Column ... selective);

    int updateByExampleSelective(@Param("record") LoanBill record, @Param("example") LoanBillExample example);

    int updateByExample(@Param("record") LoanBill record, @Param("example") LoanBillExample example);

    int updateByPrimaryKeySelective(LoanBill record);

    int updateByPrimaryKey(LoanBill record);

    LoanBill selectOneByExample(LoanBillExample example);

    LoanBill selectOneByExampleSelective(@Param("example") LoanBillExample example, @Param("selective") LoanBill.Column ... selective);

    int batchInsert(@Param("list") List<LoanBill> list);

    int batchInsertSelective(@Param("list") List<LoanBill> list, @Param("selective") LoanBill.Column ... selective);
}