package com.demo.hzed.persistence.auto.mapper;

import com.demo.hzed.persistence.auto.entity.Bill;
import com.demo.hzed.persistence.auto.entity.example.BillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillMapper {
    long countByExample(BillExample example);

    int deleteByExample(BillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    List<Bill> selectByExample(BillExample example);

    List<Bill> selectByExampleSelective(@Param("example") BillExample example, @Param("selective") Bill.Column ... selective);

    Bill selectByPrimaryKey(Long id);

    Bill selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Bill.Column ... selective);

    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    Bill selectOneByExample(BillExample example);

    Bill selectOneByExampleSelective(@Param("example") BillExample example, @Param("selective") Bill.Column ... selective);

    int batchInsert(@Param("list") List<Bill> list);

    int batchInsertSelective(@Param("list") List<Bill> list, @Param("selective") Bill.Column ... selective);
}