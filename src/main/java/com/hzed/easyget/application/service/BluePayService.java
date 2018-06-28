package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.BankTypeEnum;
import com.hzed.easyget.application.enums.RepayFlowJobEnum;
import com.hzed.easyget.application.enums.RepayMentEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangrl
 * @date 2018/6/4
 */
@Service
@Slf4j
public class BluePayService {
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private RepayRepository repayRepository;
    @Autowired
    private RestService restService;
    @Autowired
    private PayProp prop;
    @Autowired
    private BidRepository bidRepository;

    private static final String TIMEOUT = "TIMEOUT";
    private static final List<String> LISTCODE = Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(), BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.REPAYMENTS.getCode());


    /**
     * 获取va码接口
     *
     * @param request
     * @return va码构建对象
     */
    public TransactionVAResponse findVaTranc(TransactionVARequest request) {
        //查询标的信息
        Bid bid = bidRepository.findByIdWithExp(request.getBidId());
        //先查询数据库 是否存在没过期的还款码
        UserTransactionRepay repayQuery = repayRepository.getVaCodeByParmers(request.getBidId(), request.getAmount(), request.isFlag() ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue(),request.getMode());
        TransactionVAResponse vaResponse = new TransactionVAResponse();
        if (!ObjectUtils.isEmpty(repayQuery) && repayQuery.getMode().equals(request.getMode())) {
            vaResponse.setExpireTime(DateUtil.localDateTimeToTimestamp(repayQuery.getVaExpireTime()));
            vaResponse.setVaCodel(repayQuery.getVa());
            vaResponse.setMode(repayQuery.getMode());
            return vaResponse;
        }
        String payMentId = IdentifierGenerator.nextSeqNo();
        //组装请求信息
        PaymentCodeRequest paymentRequest = new PaymentCodeRequest();
        paymentRequest.setBankType(bid.getInBank().toLowerCase());
        paymentRequest.setTransactionId(payMentId);
        paymentRequest.setCardNo(bid.getInAccount());
        paymentRequest.setPrice(request.getAmount());
        paymentRequest.setMsisdn(RequestUtil.getGlobalUser().getMobile());
        paymentRequest.setPayType(RepayMentEnum.getBlue(request.getMode()));
        if (request.getMode().equals(RepayMentEnum.OTC.getMode())) {
            paymentRequest.setBankType(null);
        }
        log.info("获取还款码，bluepay请求地址{},参数：{}", prop.getAbsGetPaymentCodeUrl(), JSONObject.toJSONString(paymentRequest));
        String result = restService.doPostJson(prop.getAbsGetPaymentCodeUrl(), JSONObject.toJSONString(paymentRequest));
        log.info("获取还款码，bluepay返回数据：{}", result);
        if (result.equals(TIMEOUT)) {
            throw new ComBizException(BizCodeEnum.PAYMENTCODE_ERROR);
        }
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        //解析返回信息
        if (!response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
            throw new ComBizException(BizCodeEnum.PAYMENTCODE_ERROR);
        }
        String paymentCode = JSON.parseObject(response.getData()).getString("paymentCode");
        log.info("获取还款码，bluepay返回还款码：{}", paymentCode);
        LocalDateTime createTime = LocalDateTime.now();
        UserTransactionRepay repayInsert = UserTransactionRepay.builder()
                .id(IdentifierGenerator.nextId())
                .bidId(request.getBidId())
                .paymentId(payMentId)
                .amount(request.getAmount())
                .repaymentTime(LocalDateTime.now())
                .mode(request.getMode())
                .va(paymentCode)
                .vaCreateTime(createTime)
                .vaExpireTime(createTime.plusHours(6))
                .repaymentType(request.isFlag() ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue())
                .status(TransactionTypeEnum.INIT_RANSACTION.getCode().byteValue())
                .build();
        //插入va码到数据库
        repayRepository.insertSelective(repayInsert);
        //组装返回信息
        vaResponse.setExpireTime(DateUtil.localDateTimeToTimestamp(repayInsert.getVaExpireTime()));
        vaResponse.setMode(request.getMode());
        vaResponse.setVaCodel(paymentCode);
        return vaResponse;
    }

    /**
     * 测试还款接口
     *
     * @param request app请求后台实体
     * @return 返回状态
     */
    public PayResponse testRepayment(RepaymentCompleRequest request) {
        request.setRequestNo(IdentifierGenerator.nextSeqNo());
        log.info("完成还款接口请求地址{},报文：{}", prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        log.info("完成还款接口返回报文：{}", result);
        if (result.equals(TIMEOUT)) {
            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR);
        }
        return JSON.parseObject(result, PayResponse.class);
    }

    /**
     * 还款成功信息流
     *
     * @param userTransaction 交易对象
     * @param repayUpdate
     */
    public void repaymentSuccess(UserTransaction userTransaction, UserTransactionRepay repayUpdate) {
        // 修改交易记录
        UserTransaction userTransactionUpdate = UserTransaction.builder()
                .id(userTransaction.getId())
                .status(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue())
                .updateTime(LocalDateTime.now()).build();
        // 插入还款定时任务
        RepayInfoFlowJob repayInfoFlowJobInsert = RepayInfoFlowJob.builder()
                .id(IdentifierGenerator.nextId())
                .createTime(LocalDateTime.now())
                .transactionId(userTransaction.getId())
                .bidId(userTransaction.getBidId())
                .repaymentAmount(userTransaction.getAmount())
                .realRepaymentTime(LocalDateTime.now())
                .repaymentMode(RepayFlowJobEnum.UNDER_LINE.getCode().byteValue())
                .repaymentType(userTransaction.getRepaymentType())
                .build();
        repayRepository.afterRepayment(userTransactionUpdate, repayInfoFlowJobInsert,repayUpdate);
    }

    /**
     * 放款请求接口
     *
     * @param request bluepay请求对象
     * @return 返回请求结果
     */
    public PayResponse loanTransaction(LoanTransactionRequest request) {
        log.info("请求地址{},请求报文：{}", prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        log.info("请求地址{},返回报文：{}", prop.getAbsLoanTransactionUrl(), result);
        if (TIMEOUT.equals(result)) {
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR);
        }
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        //判断返回状态 0000 0001 0002
        if (!LISTCODE.contains(response.getCode())) {
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR);
        }
        return response;
    }


}
