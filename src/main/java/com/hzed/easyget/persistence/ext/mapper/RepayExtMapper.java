package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import org.apache.ibatis.annotations.Param;

/**
 * @description：还款相关mybatis接口
 * @author：[zhangruilin]
 * @time：2018/6/15-16:03
 **/
public interface RepayExtMapper {
    /**
     * 查询va码
     */
    UserTransactionRepay findVaTranc(@Param("payId") Long payId, @Param("mode") String mode);

    /**
     * 查询当天的交易记录
     */
    UserTransaction selectOneByExample(@Param("bidId")Long bidId,@Param("type") byte type,@Param("status") byte status,@Param("repayMentType") byte repayMentType);
}
