package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.JobStatusEnum;
import com.hzed.easyget.infrastructure.repository.BidProgressRepository;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayInfoFlowJobRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    private RepayInfoFlowJobRepository repayInfoFlowJobRepository;
    @Autowired
    private RepayService repayService;

    public void pushBid() {
//        //查询中间表，拿到所有推送资产失败大于5次的ids，次数小与等于5的可以重跑
        Map<String, Object> map = Maps.newHashMap();
        List<BidExt> bids = bidRepository.gitBidsToPush(map);
        if (bids.size() <= 0 || bids.isEmpty()) {
            return;
        }
        for (BidExt bidExt : bids) {
            Long tempId = 0L;
            try {
                Boolean isSuccess = false;
                Boolean hasRun = false;
                Integer times = 0;
                //要是已经推送过则更新重跑次数和推送时间
                List<TempTable> tempList2 = tempTableRepository.getTempByJobNameAndReId("pushBid", bidExt.getBidId());
                if (tempList2.size() > 0) {
                    tempId = tempList2.get(0).getId();
                    hasRun = true;
                    times = tempList2.get(0).getReRunTimes() + 1;
                }
                //已经跑过，update
                if (hasRun) {
                    tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).reRunTimes(times.byteValue()).build());
                } else {
                    //没跑过的新增
                    Long jobId = IdentifierGenerator.nextId();
                    tempTableRepository.insertJob(TempTable.builder().id(jobId).relaseId(bidExt.getBidId()).jobName("pushBid").remark("推送资产").createTime(LocalDateTime.now()).reRunTimes(Integer.valueOf(1).byteValue()).build());
                }
                // TODO 推送-调风控接口

                //TODO 风控结果
                isSuccess = true;
                if (isSuccess) {
                    //推送成功后 写标进度数据到t_loan_bid_progress，并删除job中数据
                    tempTableRepository.afterPushBid(buildBidProgress(bidExt.getBidId(), BidProgressTypeEnum.AUDIT.getMsg()), bidExt.getBidId());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                //TODO
                tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).remark(ex.getMessage()).build());
            }
        }
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
