package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.RepayFlowJobEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.PaymentCodeRequest;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.controller.model.TransactionVAResponse;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.RepayRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
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

    private static final String TIMEOUT = "TIMEOUT";
    private static final List<String> LISTCODE = Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(), BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.REPAYMENTS.getCode());


    /**
     * 获取va码
     *
     * @param payId 交易流水id
     * @param mode  交易方式
     * @return va码构建对象
     */
    public TransactionVAResponse findVaTranc(Long payId, String mode) {
        //先查询数据库 是否存在没过期的还款码
        UserTransactionRepay repayQuery = repayRepository.findVaTranc(payId, mode);
        TransactionVAResponse vaResponse = new TransactionVAResponse();
        if (!ObjectUtils.isEmpty(repayQuery)) {
            vaResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(repayQuery.getVaCreateTime()));
            vaResponse.setVaCodel(repayQuery.getVa());
            vaResponse.setMode(repayQuery.getMode());
            return vaResponse;
        }
        //当数据库为空 或者va码失效了
        UserTransaction transactionQuery = repayRepository.findById(payId);
        //组装请求信息
        PaymentCodeRequest paymentRequest = new PaymentCodeRequest();
        paymentRequest.setBankType(transactionQuery.getBank().toLowerCase());
        paymentRequest.setTransactionId(transactionQuery.getPaymentId());
        paymentRequest.setCardNo(transactionQuery.getAccount());
        paymentRequest.setPrice(transactionQuery.getAmount());
//        paymentRequest.setMsisdn(RequestUtil.getGlobalUser().getMobile());
        paymentRequest.setMsisdn("8615926633889");
        paymentRequest.setPayType(mode.toLowerCase());
        if (mode.equals(ComConsts.OTC)) {
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
        UserTransactionRepay repayInsert = UserTransactionRepay.builder()
                .id(IdentifierGenerator.nextId())
                .mode(mode)
//                .transactionId(payId)
                .va(paymentCode)
                .vaCreateTime(LocalDateTime.now()).build();
        //插入va码到数据库
        repayRepository.insertSelective(repayInsert);
        //组装返回信息
        vaResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(repayInsert.getVaCreateTime()));
        vaResponse.setMode(mode);
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
        //先校验
        if (request.getPayType().equals(ComConsts.ATM)) {
            if (ObjectUtils.isEmpty(request.getMsisdn()) || ObjectUtils.isEmpty(request.getBankType())) {
                throw new ComBizException(BizCodeEnum.REPAYMENT_INFORMATION_ERROR);
            }
            if (request.getBankType().equals(ComConsts.PERMATA) || request.getBankType().equals(ComConsts.BNI)) {
                throw new ComBizException(BizCodeEnum.REPAYMENT_INFORMATION_ERROR);
            }
        }
        //先查询交易信息比对数据
        UserTransaction transactionQuery = userTransactionRepository.findOldTranceByExample(request.getTransactionId(), TransactionTypeEnum.OUT.getCode().byteValue(), TransactionTypeEnum.INIT_RANSACTION.getCode().byteValue());
        if (ObjectUtils.isEmpty(transactionQuery)) {
            throw new ComBizException(BizCodeEnum.USERTRANSACTION_ERROR);
        }
        //组装请求报文
        request.setPayType(request.getPayType().toLowerCase());
//        request.setMsisdn("8615926633889");
        if (!request.getPayType().equals(ComConsts.OTC)) {
            request.setBankType(request.getPayType().toLowerCase());
        }
        request.setRequestNo(IdentifierGenerator.nextSeqNo());
        log.info("完成还款接口请求地址{},报文：{}", prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        log.info("完成还款接口返回报文：{}", result);
        if (result.equals(TIMEOUT)) {
            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR);
        }
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        //判断返回状态 0000 0001 0002
        if (!LISTCODE.contains(response.getCode())) {
            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR);
        }
        //直接处理成功
        if (response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
            this.repaymentSuccess(transactionQuery);
        } else {
            //修改状态 交易中
            UserTransaction userTransaction = UserTransaction.builder().id(transactionQuery.getId()).status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue()).build();
            repayRepository.updateByPrimaryKey(userTransaction);
        }
        //修改交易的确认时间
        LocalDateTime time = LocalDateTime.now();
//        repayRepository.updateByPrimaryKey(UserTransaction.builder().id(transactionQuery.getId()).confirmTime(time).build());
        response.setConfirmTime(DateUtil.localDateTimeToTimestamp(time));
        response.setCode(BizCodeEnum.SUCCESS.getCode());
        response.setPayId(transactionQuery.getId());
        return response;
    }

    /**
     * 还款成功信息流
     *
     * @param userTransaction 交易对象
     */
    public void repaymentSuccess(UserTransaction userTransaction) {
        // 修改交易记录
        UserTransaction userTransactionUpdate = new UserTransaction();
        userTransactionUpdate.setId(userTransaction.getId());
        userTransactionUpdate.setStatus(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue());
        userTransactionUpdate.setUpdateTime(LocalDateTime.now());

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
        repayRepository.afterRepayment(userTransactionUpdate, repayInfoFlowJobInsert);
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
