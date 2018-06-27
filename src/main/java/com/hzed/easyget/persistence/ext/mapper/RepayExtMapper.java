package com.hzed.easyget.persistence.ext.mapper;

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

}
