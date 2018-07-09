package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private BidRepository bidRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private TempTableRepository tempTableRepository;

    /**
     * 放款直接成功 改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
     *
     * @param bidId     标的id
     * @param tempId    推送放款记录的中间表id
     * @param paymentId 交易id
     */
    public void lendingCallback(Long bidId, Long tempId, String paymentId, Byte states, LocalDateTime overTime) {
        // 回调操作
        Bid bid = bidRepository.findById(bidId);
        //改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
        //工厂类获取bill和billLedgers
        ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
        List<Bill> bills = product.createBills(bid);
        List<BillLedger> billLedgers = product.createBillLedger(bills, bid.getLoanAmount(), bid.getPeriod());
        UserTransaction transaction = buildUserTransaction(bid.getUserId(), bidId, TransactionTypeEnum.IN.getCode().byteValue(), bid.getLoanAmount().subtract(bid.getAuditFee()), paymentId, bid.getInBank(), bid.getInAccount(), states, overTime);
        tempTableRepository.afterBankLoan(
                Bid.builder().id(bidId).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).updateTime(LocalDateTime.now()).build(),
                BidProgress.builder().bidId(bidId).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").handleTime(LocalDateTime.now()).build(),
                bills.get(0),
                billLedgers,
                tempId,
                transaction,
                true
        );
    }

    /**
     * 构建交易对象
     *
     * @param userId    用户
     * @param bidId     标的
     * @param type      类型'交易类型 1-入账 2-出账 3-其他',
     * @param amount    金额
     * @param paymentId 交易id
     * @param bank      银行
     * @param account   账号
     * @param states    交易状态
     * @param overTime  交易完成时间
     * @return
     */
    private UserTransaction buildUserTransaction(Long userId, Long bidId, Byte type, BigDecimal amount, String paymentId, String bank, String account, Byte states, LocalDateTime overTime) {
        UserTransaction transaction = new UserTransaction();
        transaction.setId(IdentifierGenerator.nextId());
        transaction.setUserId(userId);
        transaction.setBidId(bidId);
        transaction.setType(type);
        transaction.setRepaymentType(TransactionTypeEnum.IN.getCode().byteValue());
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
     *
     * @param bidNo
     * @param transactionId
     * @param overTime      交易完成时间
     * @param states        交易状态
     */
    public void insertUsrTransactionInfo(Long bidNo, String transactionId, Byte states, LocalDateTime overTime) {
        Bid bidInfo = bidRepository.findById(bidNo);
        UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bidNo, TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount().subtract(bidInfo.getAuditFee()), transactionId, bidInfo.getInBank(), bidInfo.getInAccount(), states, overTime);
        userTransactionRepository.insertSelective(transaction);
    }

    /**
     * 根据交易id 交易类型 查询交易记录
     *
     * @param paymnetId
     * @return 交易记录
     */
    public UserTransaction findUserTranByPaymentId(String paymnetId, Byte type) {
        return userTransactionRepository.findUserTranByPaymentId(paymnetId, type);
    }

    /**
     * 放款成功
     * 1、改标的状态
     * 2、生成账单：砍头息、插入账单、台账、标进度、交易记录表
     * 2、删除中间表数据
     *
     * @param userTransaction 交易记录
     * @param tempId          中间表id
     */
    public void loanSuccess(UserTransaction userTransaction, Long tempId) {
        Long bidId = userTransaction.getBidId();
        String paymentId = userTransaction.getPaymentId();
        // 待修改标
        Bid bid = bidRepository.findById(bidId);
        // 相关账单
        ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
        List<Bill> bills = product.createBills(bid);
        List<BillLedger> billLedgers = product.createBillLedger(bills, bid.getLoanAmount(), bid.getPeriod());

        UserTransaction transaction = buildUserTransaction(bid.getUserId(), bidId, TransactionTypeEnum.IN.getCode().byteValue(), bid.getLoanAmount().subtract(bid.getAuditFee()), paymentId, bid.getInBank(), bid.getInAccount(), TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), LocalDateTime.now());
        tempTableRepository.afterBankLoan(
                Bid.builder().id(bidId).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).updateTime(LocalDateTime.now()).build(),
                BidProgress.builder().bidId(bidId).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").handleTime(LocalDateTime.now()).build(),
                bills.get(0),
                billLedgers,
                tempId,
                transaction,
                false
        );
    }

    /**
     * 修改交易记录状态
     */
    public void updateUserTranState(String paymentId, byte b) {
        UserTransaction userTransaction = UserTransaction.builder().paymentId(paymentId).status(b).updateTime(LocalDateTime.now()).build();
        bidRepository.updateUserTranStatus(userTransaction);
    }
}