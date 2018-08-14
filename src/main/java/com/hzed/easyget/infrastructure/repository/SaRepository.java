package com.hzed.easyget.infrastructure.repository;


import com.hzed.easyget.persistence.ext.entity.SaExt;
import com.hzed.easyget.persistence.ext.mapper.SaExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * @author wenhuaping
 * @date 2018-06-25
 */
@Slf4j
@Repository
public class SaRepository {

    @Autowired
    private SaExtMapper saExtMapper;

    public List<SaExt> inDataList(Date startDate, Date endDate) {
        return saExtMapper.queryInDataInfo(startDate, endDate);
    }

    public void batchSaveInData(List<SaExt> list) {
        saExtMapper.batchSaveInData(list);
    }

    public List<SaExt> pushInDataList(Date startDate, Date endDate) {
        return saExtMapper.pushInDataList(startDate, endDate);
    }

    public int calculateLoanTimes(long userId) {
        return saExtMapper.calculateLoanTimes(userId);
    }

    public void updateToDbInDataDetail(Long bidId, Long userId, int loanTimes) {
        saExtMapper.updateToDbInDataDetail(bidId, userId, loanTimes);
    }

    public List<SaExt> loanSuccessList(Date startDate, Date endDate) {
        return saExtMapper.loanSuccessList(startDate, endDate);
    }

    public void batchSaveLoanSuccess(List<SaExt> list) {
        saExtMapper.batchSaveLoanSuccess(list);
    }

    public List<SaExt> pushLoanSuccessList(Date startDate, Date endDate) {
        return saExtMapper.pushLoanSuccessList(startDate, endDate);
    }

    public void updateToDbLoanSuccessDetail(Long bidId, Long userId, int loanTimes) {
        saExtMapper.updateToDbLoanSuccessDetail(bidId, userId, loanTimes);
    }

    public List<SaExt> repaymentSuccessList(Date startDate, Date endDate) {
        return saExtMapper.repaymentSuccessList(startDate, endDate);
    }

    public void batchSaveRepaymentSuccess(List<SaExt> list) {
        saExtMapper.batchSaveRepaymentSuccess(list);
    }

    public List<SaExt> pushRepaymentSuccessList(Date startDate, Date endDate) {
        return saExtMapper.pushRepaymentSuccessList(startDate, endDate);
    }

    public void updateToDbRepaymentSuccessDetail(Long bidId, Long userId) {
        saExtMapper.updateToDbRepaymentSuccessDetail(bidId, userId);
    }

    public int findInDataInfo(Long bidId, Long userId, Byte bidStatus) {
        return saExtMapper.findInDataInfo(bidId, userId, bidStatus);
    }

    public int findLoanSuccess(Long bidId, Long userId) {
        return saExtMapper.findLoanSuccess(bidId, userId);
    }

    public int findRepaymentSuccess(Long bidId, Long userId) {
        return saExtMapper.findRepaymentSuccess(bidId, userId);
    }

    public void saOperator(SaExt saExt) {
        saExtMapper.saOperator(saExt);
    }
}
