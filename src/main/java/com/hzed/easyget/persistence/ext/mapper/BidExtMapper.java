package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author hfj
 * @Date 2108/6/8
 */
public interface BidExtMapper {
    /**
     * 查找要推送的资产
     */
    List<BidExt> selectBidsToPush();
    /**
     * 查找要放款的标
     */
    List<BidExt> findBankLoanBids();

    /**
     * 查询放款信息
     * @param bidId
     * @return
     */
    LoanTransactionRequest findLoanTransaction(@Param("bidId") Long bidId);

    /**
     * 修改交易表状态
     * @param transaction
     */
    void updateUserTranceOverstate(UserTransaction transaction);

    /**
     * 查询放款推送任务
     * @param bidId
     * @param pushBankTask
     * @return
     */
    Long findTempTableByBidNoAndName(@Param("bidNo") Long bidId,@Param("taskName") String pushBankTask);
}


