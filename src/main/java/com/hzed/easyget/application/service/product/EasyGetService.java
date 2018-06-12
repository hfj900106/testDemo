package com.hzed.easyget.application.service.product;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BillLedgerItemEnum;
import com.hzed.easyget.application.enums.BillStatusEnum;
import com.hzed.easyget.application.service.product.model.BillInfo;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 印尼产品服务类
 *
 * @author guichang
 * @date 2018/6/2
 */

public class EasyGetService implements ProductService {

    @Override
    public BillInfo createBillInfo(Bid bid) {
        checkBid(bid);

        // TODO
        return null;
    }

    @Override
    public List<Bill> createBills(Bid bid) {
        List<Bill> lists = Lists.newArrayList();
        lists.add(buildBill(bid.getId() ,new EasyGetProduct(bid.getLoanAmount()).getRepaymentAmount()));
        return lists;
    }

    @Override
    public List<BillLedger> createBillLedger(Bill bill,Bid bid) {
        List<BillLedger> lists = Lists.newArrayList();
        //本金台账
        lists.add(buildBillLedger( bill.getId(),new EasyGetProduct(bid.getLoanAmount()).getAmount(), BillLedgerItemEnum.CORPUS.getCode().byteValue()));
        //砍头息台账
        lists.add(buildBillLedger( bill.getId(),new EasyGetProduct(bid.getLoanAmount()).getTailFee(),BillLedgerItemEnum.TAIL_FEE.getCode().byteValue()));
        return lists;
    }

    @Override
    public List<BillLedger> createBillLedger(Bid bid) {
        List<BillLedger> result = Lists.newArrayList();
        List<Bill> bills = createBills(bid);
        bills.forEach(bill -> {
            result.addAll(createBillLedger(bill,bid));
        });
        return result;
    }

    @Override
    public void checkBid(Bid bid) {
        // TODO 产品信息
        if(bid.getPeriod()!=15){
            throw new ComBizException(BizCodeEnum.SERVICE_EXCEPTION, "标ID：" + bid.getId() + " 不是印尼15天产品");
        }
    }

    private Bill buildBill( Long bidId ,BigDecimal repaymentAmount){
        Bill bill = new Bill();
        bill.setId(IdentifierGenerator.nextId());
        bill.setBidId(bidId);
        //TODO
        bill.setIndexPeriods(1);
        bill.setRepaymentTime(DateUtil.addDays(LocalDateTime.now(),15));
        bill.setRepaymentAmount(repaymentAmount);
        bill.setRealRepaymentAmount(new BigDecimal(0));
        bill.setStatus(BillStatusEnum.WAIT_CLEAR.getCode().byteValue());
        bill.setIsPartialRepayment(false);
        bill.setCreateTime(LocalDateTime.now());
        return bill;
    }

    private BillLedger buildBillLedger(Long billId, BigDecimal amount, Byte item){
        BillLedger billLedger = new BillLedger();
        billLedger.setId(IdentifierGenerator.nextId());
        billLedger.setBillId(billId);
        //TODO
        billLedger.setRepaymentTime(DateUtil.addDays(LocalDateTime.now(),15));
        billLedger.setRepaymentAmount(amount);
        billLedger.setRepaymentItem(item);
        billLedger.setCreateTime(LocalDateTime.now());
        return billLedger;
    }



}