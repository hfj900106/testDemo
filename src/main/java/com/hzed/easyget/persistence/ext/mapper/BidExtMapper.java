package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.BidExt;

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
}


