package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Service
public class CallbackService {

    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private BidRepository bidRepository;

    public void pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        BigDecimal loanAmount = request.getLoanAmount();
        String resultCode = request.getResultCode();
        LocalDateTime dateTime = request.getHandleTime();
        tempTableRepository.pushBidCallback(
                Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(dateTime)
                        .status("1".equals(resultCode) ? BidStatusEnum.AUDIT_PASS.getCode().byteValue() : BidStatusEnum.AUDIT_FAIL.getCode().byteValue()).build(),
                BidProgress.builder().bidId(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.AUDIT.getCode().byteValue())
                        .createTime(dateTime).build(),
                bidId);
    }

    /**
     * 放款状态回调 改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
     * @param bidNo 标的编号
     * @param tempId 推送放款记录的中间表id
     * @param requestSeq 交易流水
     */
    public void lendingCallback(Long bidNo,Long tempId, String requestSeq,boolean isOver,LocalDateTime overTime){
        // 回调操作
        Bid bidInfo = bidRepository.findById(bidNo);
        //改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
        //工厂类获取bill和billLedgers
        ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
        List<Bill> bills = product.createBills(bidInfo);
        List<BillLedger> billLedgers = product.createBillLedger(bidInfo);
        //TODO 中间表id 有待查询
        UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bidNo, TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount(), requestSeq,bidInfo.getInBank() ,bidInfo.getInAccount(),isOver,overTime );
        tempTableRepository.afterBankLoan(
                Bid.builder().id(bidNo).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).auditFee(new EasyGetProduct(bidInfo.getLoanAmount()).getHeadFee()).updateTime(LocalDateTime.now()).build(),
                BidProgress.builder().bidId(bidNo).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").build(),
                bills.get(0),
                billLedgers,
                tempId,
                transaction
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
     * @param isOver 是否交易完成
     * @param overTime 交易完成时间
     * @return
     */
    private UserTransaction buildUserTransaction(Long userId, Long bidId, Byte type, BigDecimal amount,String paymentId, String bank, String account,boolean isOver,LocalDateTime overTime) {
        UserTransaction transaction = new UserTransaction();
        transaction.setId(IdentifierGenerator.nextId());
        transaction.setUserId(userId);
        transaction.setBidId(bidId);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setPaymentId(paymentId);
        transaction.setIsOver(isOver);
        transaction.setUpdateTime(overTime);
        transaction.setBank(bank);
        transaction.setAccount(account);
        transaction.setCreateTime(LocalDateTime.now());
        transaction.setRemark("放款");
        return transaction;
    }

}
