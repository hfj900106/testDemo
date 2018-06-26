package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author hfj
 * @Date 2108/6/8
 */
public interface BidExtMapper {
    /**
     * 查找要推送风控或者放款的标
     */
    List<BidExt> selectBidsToPushOrBankLoan(@Param("status") Byte status,@Param("times") Byte times,@Param("jobName") String jobName);
    /**
     * 查询放款信息
     * @param bidId
     * @return
     */
    LoanTransactionRequest findLoanTransaction(@Param("bidId") Long bidId);

}


