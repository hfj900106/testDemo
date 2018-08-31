package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.BillExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BillExtMapper {

    /**
     * 找出未结清账单对应用户的手机，应还时间
     * @return
     */
    List<BillExt> findUnRepayBillExt(@Param("day") Integer day);


}


