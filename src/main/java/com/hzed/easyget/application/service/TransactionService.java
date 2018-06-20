package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.ReceiverTransactionRequest;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.infrastructure.utils.FaJsonUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 交易相关
 * 放款和收款
 *
 * @author wuchengwu
 * @data 2018/6/9
 */
@Slf4j
@Service
public class TransactionService {

    @Autowired
    private RestService restService;
    @Autowired
    private PayProp prop;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private TempTableRepository tempTableRepository;

    /**
     * 放款
     */
    public PayResponse loanTransaction(LoanTransactionRequest request) {
        List<String> listCode= Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(),BizCodeEnum.SUCCESS.getCode(),BizCodeEnum.REPAYMENTS.getCode());
        log.info("支付放款接口请求报文：{}", JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        if(result.equals(BizCodeEnum.TIMEOUT.getCode())){
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR,BizCodeEnum.TIMEOUT.getMessage());
        }
        log.info("支付放款接口返回报文：{}", result);
        PayResponse response=JSON.parseObject(result,new TypeReference<PayResponse>() {});
        //判断返回状态 0000 0001 0002
        if (!listCode.contains(response.getCode())) {
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR, response.getMsg());
        }
        return response;
    }


    /**
     * 收款
     */
    public Response receiverTransaction(ReceiverTransactionRequest request) {
        log.info("支付收款接口请求报文：{}", FaJsonUtil.objToString(request));
        Response response = restService.postJson(prop.getReceiverTransactionUrl(), request, Response.class);
        log.info("支付收款接口返回报文：{}", FaJsonUtil.objToString(response));
        if (!BizCodeEnum.SUCCESS.equals(response.getCode())) {

            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR, response.getMessage());
        }
        return Response.getSuccessResponse();
    }
    /**
     * 放款直接成功 改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
     * @param bidNo 标的编号
     * @param tempId 推送放款记录的中间表id
     * @param paymentId 交易id
     */
    public void lendingCallback(Long bidNo,Long tempId, String paymentId,Byte states,LocalDateTime overTime){
        // 回调操作
        Bid bidInfo = bidRepository.findById(bidNo);
        //改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
        //工厂类获取bill和billLedgers
        ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
        List<Bill> bills = product.createBills(bidInfo);
        List<BillLedger> billLedgers = product.createBillLedger(bidInfo);
        UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bidNo, TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount(), paymentId,bidInfo.getInBank() ,bidInfo.getInAccount(),states,overTime );
        tempTableRepository.afterBankLoan(
                Bid.builder().id(bidNo).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).auditFee(new EasyGetProduct(bidInfo.getLoanAmount()).getHeadFee()).updateTime(LocalDateTime.now()).build(),
                BidProgress.builder().bidId(bidNo).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").handleTime(LocalDateTime.now()).build(),
                bills.get(0),
                billLedgers,
                tempId,
                transaction,
                true
        );
    }

    /**
     * 构建交易对象
     * @param userId 用户
     * @param bidId 标的
     * @param type 类型'交易类型 1-入账 2-出账 3-其他',
     * @param amount 金额
     * @param paymentId 交易id
     * @param bank 银行
     * @param account 账号
     * @param states 交易状态
     * @param overTime 交易完成时间
     * @return
     */
    private UserTransaction buildUserTransaction(Long userId, Long bidId, Byte type, BigDecimal amount, String paymentId, String bank, String account, Byte states, LocalDateTime overTime) {
        UserTransaction transaction = new UserTransaction();
        transaction.setId(IdentifierGenerator.nextId());
        transaction.setUserId(userId);
        transaction.setBidId(bidId);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setPaymentId(paymentId);
        transaction.setStatus(states);
        transaction.setUpdateTime(overTime);
        transaction.setBank(bank);
        transaction.setAccount(account);
        transaction.setCreateTime(LocalDateTime.now());
        transaction.setRemark("放款");
        return transaction;
    }

    /**
     * 放款进行中 插入交易记录
     * @param bidNo
     * @param transactionId
     * @param overTime 交易完成时间
     * @param states 交易状态
     */
    public void insertUsrTransactionInfo(Long bidNo, String transactionId,Byte states,LocalDateTime overTime) {
        Bid bidInfo = bidRepository.findById(bidNo);
        UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bidNo, TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount(), transactionId,bidInfo.getInBank() ,bidInfo.getInAccount(),states,overTime );
        userTransactionRepository.insertSelective(transaction);
    }

    /**
     * 根据交易id 查询交易记录
     * @param paymnetId
     * @return
     */
    public List<UserTransaction> findUserTranBypayMenid(String paymnetId) {
        return userTransactionRepository.findUserTranBypayMenid(paymnetId);
    }

    /**
     *放款回调成功  改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
     * @param userTransaction
     */
    public void callBackupdateUserTrance(UserTransaction userTransaction,Long tempId) {
        Long bidNo=userTransaction.getBidId();
        String paymentId=userTransaction.getPaymentId();
        // 回调操作
        Bid bidInfo = bidRepository.findById(bidNo);
        //改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
        //工厂类获取bill和billLedgers
        ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
        List<Bill> bills = product.createBills(bidInfo);
        List<BillLedger> billLedgers = product.createBillLedger(bidInfo);
        UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bidNo, TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount(), paymentId,bidInfo.getInBank() ,bidInfo.getInAccount(),TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), LocalDateTime.now() );
        tempTableRepository.afterBankLoan(
                Bid.builder().id(bidNo).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).auditFee(new EasyGetProduct(bidInfo.getLoanAmount()).getHeadFee()).updateTime(LocalDateTime.now()).build(),
                BidProgress.builder().bidId(bidNo).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").handleTime(LocalDateTime.now()).build(),
                bills.get(0),
                billLedgers,
                tempId,
                transaction,
                false
        );
    }

    /**
     * 修改交易记录状态
     * @param t_id
     * @param b
     */
    public void updateUserTranState(String t_id, byte b) {
        bidRepository.updateUserTranState(t_id,b);
    }
}