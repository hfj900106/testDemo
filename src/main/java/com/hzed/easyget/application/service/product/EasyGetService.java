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
        //TODO
        return null;
    }

    @Override
    public List<Bill> createBills(Bid bid) {
        List<Bill> lists = Lists.newArrayList();
        lists.add(buildBill(bid.getId(), new EasyGetProduct(bid.getLoanAmount()).getRepaymentAmount(), bid.getPeriod()));
        return lists;
    }

    @Override
    public List<BillLedger> createBillLedger(Bid bid) {
        List<BillLedger> lists = Lists.newArrayList();
        List<Bill> bills = createBills(bid);
        bills.forEach(bill -> {
            // 台账公共参数
            BillLedger billLedger = new BillLedger();
            billLedger.setId(IdentifierGenerator.nextId());
            billLedger.setBillId(bill.getId());
            billLedger.setRepaymentTime(DateUtil.addDays(LocalDateTime.now(), bid.getPeriod()));
            billLedger.setCreateTime(LocalDateTime.now());

            //本金台账
            billLedger.setRepaymentAmount(new EasyGetProduct(bid.getLoanAmount()).getAmount());
            billLedger.setRepaymentItem(BillLedgerItemEnum.CORPUS.getCode().byteValue());
            lists.add(billLedger);
            //尾款台账
            billLedger.setRepaymentAmount(new EasyGetProduct(bid.getLoanAmount()).getTailFee());
            billLedger.setRepaymentItem(BillLedgerItemEnum.TAIL_FEE.getCode().byteValue());
            lists.add(billLedger);
        });
        return lists;
    }

    @Override
    public void checkBid(Bid bid) {
        if (!bid.getProductCode().equals(ProductCodeEnum.INDONESIA.getCode())) {
            throw new ComBizException(BizCodeEnum.NOT_INDONESIA_PRODUCT, bid.getId());
        }
    }

    private Bill buildBill(Long bidId, BigDecimal repaymentAmount, Integer period) {
        Bill bill = new Bill();
        bill.setId(IdentifierGenerator.nextId());
        bill.setBidId(bidId);
        bill.setIndexPeriods(1);
        bill.setRepaymentTime(DateUtil.addDays(LocalDateTime.now(), period));
        bill.setRepaymentAmount(repaymentAmount);
        bill.setRealRepaymentAmount(new BigDecimal(0));
        bill.setStatus(BillStatusEnum.WAIT_CLEAR.getCode().byteValue());
        bill.setIsPartialRepayment(false);
        bill.setCreateTime(LocalDateTime.now());
        return bill;
    }

}