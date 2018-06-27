package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.BillStatusEnum;
import com.hzed.easyget.application.enums.RepayTypeEnum;
import com.hzed.easyget.infrastructure.config.SaProp;
import com.hzed.easyget.infrastructure.consts.SaConsts;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.SaRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.persistence.ext.entity.SaExt;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.*;

/**
 * @author wenhuaping
 * @date 2018-06-19
 */
@Slf4j
@Service
public class SaService {

    @Autowired
    private SaRepository saRepository;
    @Autowired
    private SaProp saProp;


    public void saInData() {
        try {
            log.info("SensorsAnalytics inData begin.");

            List<SaExt> list = saRepository.inDataList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(list)){
                List<SaExt> insert = distinctInDataInfo(list);
                if (!ObjectUtils.isEmpty(insert)) {
                    initInData(insert);
                    log.info("SensorsAnalytics inData, Initialization Data Succeed！");
                } else {
                    log.warn("SensorsAnalytics inData, Initialization Data is null or is empty!");
                    return;
                }
            } else {
                log.warn("SensorsAnalytics inData, Initialization Data is null or is empty!");
                return;
            }

            List<SaExt> pushList = saRepository.pushInDataList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(pushList)){
                for (SaExt info : pushList) {
                    pushInData(info);
                }
                log.info("SensorsAnalytics inData, Push Data Succeed！");
            } else {
                log.warn("SensorsAnalytics inData, Push Data is null or is empty!");
                return;
            }

            log.info("SensorsAnalytics inData end.");
        } catch (Exception e) {
            log.error("SensorsAnalytics inData, Push Data exception : ", e);
        }
    }

    /**
     * 过滤进件重复数据
     * @param inDataInfoList
     * @return
     */
    public List<SaExt> distinctInDataInfo(List<SaExt> inDataInfoList) {
        List<SaExt> list = new ArrayList<>();
        for (SaExt info : inDataInfoList){
            int count = saRepository.findInDataInfo(info.getBidId(), info.getUserId());
            if (count == 0) {
                list.add(info);
            }
        }
       return list;
    }

    public void pushInData(SaExt info) {
        boolean isSuccess = false;
        String auditSuggest = "";
        if (BidStatusEnum.AUDIT_FAIL.equals(info.getBidStatus())){
            isSuccess = false;
            auditSuggest = "人工审核不通过";
        } else if(BidStatusEnum.AUDIT_PASS.equals(info.getBidStatus())){
            isSuccess = true;
            auditSuggest = "审核通过";
        }

        Map<String, Object> properties = new HashMap<>(16);
        //  InDataID   进件订单ID(标的id)	 字符串
        properties.put("InDataID", info.getBidId());
        // UserId	借款用户ID	字符串
        properties.put("UserId", info.getUserId());
        // IsSuccess	是否成功	BOOL
        properties.put("IsSuccess", isSuccess);
        // EventResult	事件结果	字符串
        properties.put("EventResult", auditSuggest);
        // ProductType	产品名称	字符串
        properties.put("ProductType", SaConsts.projectName);
        int loanTimes = calculateLoanTimes(info.getUserId());
        // LoanTimes	第几次借款	数值
        properties.put("LoanTimes", loanTimes);

        try {
            log.info("SensorsAnalytics inData track method begin, bidId:{}, userId:{}, bidStatus:{}" , info.getBidId(), info.getUserId(), info.getBidStatus());
            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(saProp.getSaLogPath()));
            sa.track(String.valueOf(info.getUserId()), true, "InData", properties);
            sa.flush();
            log.info("SensorsAnalytics inData track method end.");
            updateToDbInDataDetail(info.getBidId(), info.getUserId(), loanTimes);
        } catch (Exception e){
            log.error("SensorsAnalytics inData track method exception, bidId:{} ,info: {}", info.getBidId(), e);
        }
    }

    public void updateToDbInDataDetail(Long bidId, Long userId, int loanTimes) {
        saRepository.updateToDbInDataDetail(bidId, userId, loanTimes);
    }

    public void initInData(List<SaExt> list){
        saRepository.batchSaveInData(list);
    }

    public int calculateLoanTimes(long userId) {
        int loanTimes = 0;
        try {
            loanTimes = saRepository.calculateLoanTimes(userId);
        } catch (Exception e) {
            log.error("统计借款人借款的次数失败,用户id为[%s] ,异常信息为：%s", userId, e.getMessage());
        }
        return loanTimes;
    }

    public void saLoanSuccess() {
        try {
            log.info("SensorsAnalytics loanSuccess begin.");
            List<SaExt> list = saRepository.loanSuccessList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(list)) {
                List<SaExt> insert = distinctLoanSuccess(list);
                if (!ObjectUtils.isEmpty(insert)) {
                    initLoanSuccess(insert);
                    log.info("SensorsAnalytics loanSuccess, Initialization Data Succeed！");
                } else {
                    log.warn("SensorsAnalytics loanSuccess, Initialization Data is null or is empty!");
                }
            } else {
                log.warn("SensorsAnalytics loanSuccess, Initialization Data is null or is empty!");
                return;
            }

            List<SaExt> pushList = saRepository.pushLoanSuccessList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(pushList)){
                for (SaExt info : pushList) {
                    pushLoanSuccess(info);
                }
                log.info("SensorsAnalytics loanSuccess, Push Data Succeed！");
            } else {
                log.warn("SensorsAnalytics loanSuccess, Push Data is null or is empty!");
                return;
            }

            log.info("SensorsAnalytics loanSuccess end.");
        } catch (Exception e) {
            log.error("SensorsAnalytics loanSuccess, loanSuccess exception : ", e);
        }
    }

    public List<SaExt> distinctLoanSuccess(List<SaExt> loanSuccessList) {
        List<SaExt> list = new ArrayList<>();
        for (SaExt info : loanSuccessList) {
            int count = saRepository.findLoanSuccess(info.getBidId(), info.getUserId());
            if (count == 0) {
                list.add(info);
            }
        }
        return list;
    }

    public void pushLoanSuccess(SaExt info) {
        Map<String, Object> properties = new HashMap<>(16);
        // InDataID	     进件订单ID	字符串
        properties.put("InDataID", info.getBidId());
        // ProductType	产品名称	字符串    //1:立借、2:爱分期    ProductType	产品名称	字符串
        properties.put("ProductType", SaConsts.projectName);
        // RepaymentTime 合约还款日期	日期
        properties.put("RepaymentTime", info.getRealRepaymentTime());
        int loanTimes = calculateLoanTimes(info.getUserId());
        // LoanTimes	第几次借款	数值
        properties.put("LoanTimes", loanTimes);

        try {
            log.info("SensorsAnalytics loanSuccess track method begin, bidId:{}, userId:{}, bidStatus:{}" , info.getBidId(), info.getUserId(), info.getBidStatus());
            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(saProp.getSaLogPath()));
            sa.track(String.valueOf(info.getUserId()), true, "LoanSuccessed", properties);
            sa.flush();
            log.info("SensorsAnalytics loanSuccess track method end.");
            updateToDbLoanSuccessDetail(info.getBidId(), info.getUserId(), loanTimes);
        } catch (Exception e){
            log.error("SensorsAnalytics loanSuccess track method exception, bidId:{} ,info: {}", info.getBidId(), e);
        }
    }

    public void updateToDbLoanSuccessDetail(Long bidId, Long userId, int loanTimes) {
        saRepository.updateToDbLoanSuccessDetail(bidId, userId, loanTimes);
    }

    public void initLoanSuccess(List<SaExt> list) {
        saRepository.batchSaveLoanSuccess(list);
    }

    public void saRepaymentSuccess() {
        try {
            log.info("SensorsAnalytics repaymentSuccess begin.");
            List<SaExt> list = saRepository.repaymentSuccessList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(list)) {
                List<SaExt> insert = distinctRepaymentSuccess(list);
                if (!ObjectUtils.isEmpty(insert)) {
                    initRepaymentSuccess(insert);
                    log.info("SensorsAnalytics repaymentSuccess, Initialization Data Succeed！");
                } else {
                    log.warn("SensorsAnalytics repaymentSuccess, Initialization Data is null or is empty!");
                    return;
                }
            } else {
                log.warn("SensorsAnalytics repaymentSuccess, Initialization Data is null or is empty!");
                return;
            }

            List<SaExt> pushList = saRepository.pushRepaymentSuccessList(DateUtil.strDateToStartDate(DateUtil.formatToDay()), DateUtil.strDateToEndDate(DateUtil.formatToDay()));
            if (!ObjectUtils.isEmpty(pushList)){
                for (SaExt info : pushList) {
                    pushRepaymentSuccess(info);
                }
                log.info("SensorsAnalytics repaymentSuccess, Push Data Succeed！");
            } else {
                log.warn("SensorsAnalytics repaymentSuccess, Push Data is null or is empty!");
                return;
            }

            log.info("SensorsAnalytics repaymentSuccess end.");
        } catch (Exception e) {
            log.error("SensorsAnalytics repaymentSuccess, Push Data exception : ", e);
        }
    }

    public List<SaExt> distinctRepaymentSuccess(List<SaExt> repaymentSuccessList) {
        List<SaExt> list = new ArrayList<>();
        for (SaExt info : repaymentSuccessList) {
            int count = saRepository.findRepaymentSuccess(info.getBidId(), info.getUserId());
            if (count == 0) {
                list.add(info);
            }
        }
        return list;
    }

    public void pushRepaymentSuccess(SaExt info) {
        Map<String, Object> properties = new HashMap<>(16);
        //    InDataID	进件订单ID	字符串
        properties.put("InDataID", info.getBidId());
        //    ProductType	产品名称	字符串
        properties.put("ProductType", SaConsts.projectName);
        //    RepaymentMethod	还款方式	字符串  全额还款、部分还款
        properties.put("RepaymentMethod", isAlreadyRepayment(info));
        //    RepaymentTime	合约还款日期	日期
        properties.put("RepaymentTime", info.getRealRepaymentTime());
        //    IsUnpaid	是否有未还金额	BOOL
        properties.put("IsUnpaid", isUnpaid(info));
        //    IsOverdue	是否逾期	BOOL
        properties.put("IsUnpaid", isOverdue(info));

        try {
            log.info("SensorsAnalytics repaymentSuccess track method begin, bidId:{}, userId:{}, bidStatus:{}" , info.getBidId(), info.getUserId(), info.getBidStatus());

            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(saProp.getSaLogPath()));
            sa.track(String.valueOf(info.getUserId()), true, "Repayment", properties);
            sa.flush();
            log.info("SensorsAnalytics repaymentSuccess track method end.");
            updateToDbRepaymentSuccessDetail(info.getBidId(), info.getUserId());
        } catch (Exception e) {
            log.error("SensorsAnalytics repaymentSuccess track method exception, bidId:{} ,info: {}", info.getBidId(), e);
        }
    }

    public void updateToDbRepaymentSuccessDetail(Long bidId, Long userId) {
        saRepository.updateToDbRepaymentSuccessDetail(bidId, userId);
    }

    public Object isOverdue(SaExt info) {
        boolean isOverdue = false;
        if (BillStatusEnum.OVERDUE_CLEAR.getCode().byteValue() == info.getBillStatus()) {
            isOverdue = true;
        } else if (BillStatusEnum.NORMAL_CLEAR.getCode().byteValue() == info.getBillStatus()){
            isOverdue = false;
        }
        // TODO
        return isOverdue;
    }

    private boolean isUnpaid(SaExt info) {
        boolean isUnpaid = false;
        if (BidStatusEnum.CLEARED.getCode().byteValue() == info.getBidStatus()) {
            isUnpaid = false;
        }
        if (BidStatusEnum.REPAYMENT.getCode().byteValue() == info.getBidStatus() && BillStatusEnum.WAIT_CLEAR.getCode().byteValue() == info.getBillStatus() && RepayTypeEnum.PART.getCode().byteValue() == info.getIsPartialRepayment()) {
            isUnpaid = true;
        }
        return isUnpaid;
    }

    public String isAlreadyRepayment(SaExt info) {
        String repaymentDesc = "";
        if (BidStatusEnum.CLEARED.getCode().byteValue() == info.getBidStatus()) {
            repaymentDesc = RepayTypeEnum.CLEAR.getMsg();
        }
        if (BidStatusEnum.REPAYMENT.getCode().byteValue() == info.getBidStatus() && BillStatusEnum.WAIT_CLEAR.getCode().byteValue() == info.getBillStatus() && RepayTypeEnum.PART.getCode().byteValue() == info.getIsPartialRepayment()) {
            repaymentDesc = RepayTypeEnum.PART.getMsg();
        }
        return repaymentDesc;
    }

    public void initRepaymentSuccess(List<SaExt> list) {
        saRepository.batchSaveRepaymentSuccess(list);
    }

    public void saOperator(SaExt saExt) {
        saRepository.saOperator(saExt);
    }

    /**
     * 匿名id 绑定用户id，方便神策数据统计
     * @param userId 用户id
     * @param anonymousId 匿名id, 由app生成提供
     */
    @Async
    public void saLogin(Long userId, String anonymousId) {
        try {
            log.info("SensorsAnalytics Login, userId binding anonymousId begin, userId:{}, anonymousId:{}", userId, anonymousId);
            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(saProp.getSaLogPath()));
            sa.trackSignUp(String.valueOf(userId), anonymousId);
            log.info("SensorsAnalytics Login, userId binding anonymousId end, userId:{}, anonymousId:{}", userId, anonymousId);
        } catch (Exception e) {
            log.error("SensorsAnalytics Login trackSignUp method exception, userId:{}, anonymousId:{} ,info: {}", userId, anonymousId, e);
        }
    }

    /**
     * 神策运营商认证信息推送，存库
     * @param user 登陆用户
     * @param bool true : 认证成功，false : 认证失败
     * @param desc 认证结果描述
     */
    @Async
    public void saOperator(GlobalUser user, boolean bool, String desc) {
        try {
            SaExt saExt = new SaExt();
            saExt.setUserId(user.getUserId());
            saExt.setUserMobile(user.getMobile());
            saExt.setBool(bool);
            saExt.setDesc(desc);

            Map<String, Object> properties = new HashMap<>(16);
            // IsSuccess	是否成功	BOOL
            properties.put("IsSuccess", bool);
            // FailReason	认证失败原因	字符串
            properties.put("EventResult", desc);

            log.info("SensorsAnalytics DetailedList track method begin, userId:{}, userMobile:{}, bool:{}, desc{}" , user.getUserId(), user.getMobile(), bool, desc);
            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer(saProp.getSaLogPath()));
            sa.track(String.valueOf(user.getUserId()), true, "DetailedList", properties);
            sa.flush();
            log.info("SensorsAnalytics DetailedList track method end.");
            saOperator(saExt);
        } catch (Exception e){
            log.error("SensorsAnalytics DetailedList track method exception, userId:{}, userMobile:{}, info: {}",user.getUserId(), user.getMobile(), e);
        }
    }
}
