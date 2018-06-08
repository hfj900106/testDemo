package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.JobStatusEnum;
import com.hzed.easyget.infrastructure.repository.BidProgressRepository;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayInfoFlowJobRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hfj
 * @date 2018/6/7
 */

@Service
public class JobService {
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private BidProgressRepository progressRepository;
    @Autowired
    private RepayInfoFlowJobRepository repayInfoFlowJobRepository;
    @Autowired
    private RepayService repayService;

    public void pushBid() {
        //查出所有待推送的标
        //查询中间表，拿到所有推送资产失败大于等于5次的ids，次数小与5的可以重跑
        List<TempTable> tempList = tempTableRepository.getByJobName();
        List<Long> ids = Lists.newArrayList();
        if (tempList.size() > 0 && !tempList.isEmpty()) {
            for (TempTable temp : tempList) {
                ids.add(temp.getId());
            }
        }
        List<Bid> bidList = bidRepository.gitBidsToPush(ids);
        if (bidList.size() <= 0 || bidList.isEmpty()) {
            return;
        }
        bidList.forEach(bid -> {
            Boolean isSuccess = false;

            //要是已经推送过则更新重跑次数和推送时间
            List<TempTable> tempList2 = tempTableRepository.getByJobNameAndId(bid.getId());
            if (tempList2.size() <= 0 || tempList2.isEmpty()) {
                return;
            }
            Boolean hasRun = false;
            Integer times = 0;
            for (TempTable temp : tempList2) {
                if (bid.getId().equals(temp.getId())) {
                    hasRun = true;
                    times = temp.getReRunTimes().intValue() + 1;
                }
            }
            //已经跑过，update
            if (hasRun) {
                tempTableRepository.upDateTemp(TempTable.builder().id(bid.getId()).createTime(LocalDateTime.now()).reRunTimes(times.byteValue()).build());
            } else {
                //没跑过的新增
                Long jobId = IdentifierGenerator.nextId();
                tempTableRepository.insertJob(TempTable.builder().id(jobId).relaseId(bid.getId()).jobName("pushBid").remark("推送资产").createTime(LocalDateTime.now()).reRunTimes(Integer.valueOf(1).byteValue()).build());
            }
            // TODO 推送-调风控接口

            //推送成功后 写标进度数据到t_loan_bid_progress，并删除job中数据
            if (isSuccess) {
                tempTableRepository.afterPushBid(buildBidProgress(bid.getId(), BidProgressTypeEnum.AUDIT.getMsg()), bid.getId());
            }
        });
    }

    private BidProgress buildBidProgress(Long bidId, String result) {
        BidProgress bidProgress = new BidProgress();
        bidProgress.setId(IdentifierGenerator.nextId());
        bidProgress.setBidId(bidId);
        bidProgress.setType(BidProgressTypeEnum.AUDIT.getCode().byteValue());
        bidProgress.setHandleTime(LocalDateTime.now());
        bidProgress.setCreateTime(LocalDateTime.now());
        bidProgress.setHandleResult(result);
        bidProgress.setRemark("推送资产");
        return bidProgress;
    }

    /**
     * 还款走信息流
     * TODO 日志打印
     */
    public void repayInfoFlow() {
        List<RepayInfoFlowJob> jobList = repayInfoFlowJobRepository.findJobList(Lists.newArrayList((byte) 1, (byte) 2), 2);
        if (jobList == null || jobList.isEmpty()) {
            return;
        }

        jobList.forEach(repayjob -> {
            try {
                // 走信息流
                Long bidId = repayjob.getBidId();
                BigDecimal repaymentAmount = repayjob.getRepaymentAmount();
                LocalDateTime realRepaymentTime = repayjob.getRealRepaymentTime();
                repayService.repayInformationFlow(bidId, repaymentAmount, realRepaymentTime, repayjob.getRequestseq(), repayjob);
            } catch (Exception e) {
                RepayInfoFlowJob jobUpdate = new RepayInfoFlowJob();
                jobUpdate.setId(repayjob.getId());
                jobUpdate.setStatus(JobStatusEnum.FALI.getCode().byteValue());
                jobUpdate.setTimes((byte) (repayjob.getTimes().intValue() + 1));
                jobUpdate.setUpdateTime(LocalDateTime.now());
                jobUpdate.setRemark(e.getMessage());
                repayInfoFlowJobRepository.update(jobUpdate);
            }
        });


    }


}
