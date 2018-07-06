package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.BillExt;
import com.hzed.easyget.persistence.ext.entity.TransactionExt;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface BillExtMapper {

    /**
     * 找出未结清账单对应用户的手机，应还时间
     * @return
     */
    List<BillExt> findUnRepayBillExt();


}


