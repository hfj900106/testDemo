package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.SaExt;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface SaExtMapper {

    /**
     * 查询当天进件记录
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> queryInDataInfo(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 批量初始化当天进件记录
     * @param list
     */
    void batchSaveInData(@Param("list") List<SaExt> list);

    /**
     * 查询当天进件推送列表
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> pushInDataList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 统计用户贷款的次数
     * @param userId
     * @return
     */
    int calculateLoanTimes(@Param("userId") long userId);

    /**
     * 更新当天已经推送的进件数据
     * @param bidId
     * @param userId
     * @param loanTimes
     */
    void updateToDbInDataDetail(@Param("bidId") Long bidId, @Param("userId") Long userId, @Param("loanTimes") int loanTimes);

    /**
     * 查询当天借款成功记录
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> loanSuccessList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 批量初始化当天借款成功记录
     * @param list
     */
    void batchSaveLoanSuccess(@Param("list") List<SaExt> list);

    /**
     * 查询当天借款成功推送列表
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> pushLoanSuccessList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 更新当天已经推送的借款成功数据
     * @param bidId
     * @param userId
     * @param loanTimes
     */
    void updateToDbLoanSuccessDetail(@Param("bidId") Long bidId, @Param("userId") Long userId, @Param("loanTimes") int loanTimes);

    /**
     * 查询当天还款记录列表
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> repaymentSuccessList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 批量初始化当天还款记录
     * @param list
     */
    void batchSaveRepaymentSuccess(@Param("list") List<SaExt> list);

    /**
     * 推送还款成功数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaExt> pushRepaymentSuccessList(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * 更新当天已经推送的还款成功数据
     * @param bidId
     * @param userId
     */
    void updateToDbRepaymentSuccessDetail(@Param("bidId") Long bidId, @Param("userId") Long userId);

    /**
     * 查找进件的数据是否存在
     * @param bidId
     * @param userId
     * @return
     */
    int findInDataInfo(@Param("bidId") Long bidId, @Param("userId") Long userId, @Param("bidStatus") Byte bidStatus);

    /**
     * 查找借款成功的数据是否存在
     * @param bidId
     * @param userId
     * @return
     */
    int findLoanSuccess(@Param("bidId") Long bidId, @Param("userId") Long userId);

    /**
     * 查找还款成功的数据是否存在
     * @param bidId
     * @param userId
     * @return
     */
    int findRepaymentSuccess(@Param("bidId") Long bidId,  @Param("userId") Long userId);

    /**
     * 神策-运营商认证
     * @param saExt
     */
    void saOperator(SaExt saExt);

    /**
     * 神策-通讯录授权
     * @param saExt
     */
    void saveContactsData(SaExt saExt);

    /**
     * 神策-短信认证
     * @param saExt
     */
    void saveSMSData(SaExt saExt);
}
