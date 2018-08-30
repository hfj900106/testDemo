package com.hzed.easyget.application.service.product;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BillLedgerItemEnum;
import com.hzed.easyget.application.enums.BillStatusEnum;
import com.hzed.easyget.application.enums.ProductTypeEnum;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.repository.ProductRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;
import com.hzed.easyget.persistence.auto.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 印尼产品服务类
 *
 * @author guichang
 * @date 2018/6/2
 */

@Service
public class EasyGetServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Bill> createBills(Bid bid) {
        checkBid(bid);

        Bill bill = new Bill();
        bill.setId(IDGenerator.nextId());
        bill.setBidId(bid.getId());
        bill.setIndexPeriods(1);
        bill.setRepaymentTime(getRepaymentTime(bid.getPeriod()));
        bill.setRepaymentAmount(createProduct(bid.getLoanAmount(), bid.getPeriod()).getTotalRepaymentAmount());
        bill.setRealRepaymentAmount(new BigDecimal(0));
        bill.setStatus(BillStatusEnum.WAIT_CLEAR.getCode().byteValue());
        bill.setIsPartialRepayment(false);
        bill.setCreateTime(LocalDateTime.now());

        return Lists.newArrayList(bill);
    }

    @Override
    public List<BillLedger> createBillLedger(List<Bill> bills, BigDecimal amount, Integer period) {
        AbstractProduct product = createProduct(amount, period);

        List<BillLedger> lists = Lists.newArrayList();
        bills.forEach(bill -> {
            // 本金台账
            BillLedger billLedger1 = new BillLedger();
            billLedger1.setBillId(bill.getId());
            billLedger1.setRepaymentTime(getRepaymentTime(period));
            billLedger1.setId(IDGenerator.nextId());
            billLedger1.setRepaymentAmount(amount);
            billLedger1.setRepaymentItem(BillLedgerItemEnum.CORPUS.getCode().byteValue());
            lists.add(billLedger1);
            // 尾款台账
            BillLedger billLedger2 = new BillLedger();
            billLedger2.setBillId(bill.getId());
            billLedger2.setRepaymentTime(getRepaymentTime(period));
            billLedger2.setId(IDGenerator.nextId());
            billLedger2.setRepaymentAmount(product.getTailFee());
            billLedger2.setRepaymentItem(BillLedgerItemEnum.TAIL_FEE.getCode().byteValue());
            lists.add(billLedger2);
        });
        return lists;
    }

    @Override
    public void checkBid(Bid bid) {
        if (!bid.getProductCode().equals(ProductCodeEnum.INDONESIA.getCode())) {
            throw new ComBizException(BizCodeEnum.NOT_INDONESIA_PRODUCT, new Object[]{String.valueOf(bid.getId())});
        }
    }

    @Override
    public AbstractProduct createProduct(BigDecimal amount, Integer days) {
        Product productConf = productRepository.findByCode(ProductTypeEnum.PRODUCT_CODE.getCode());

        EasyGetProduct product = new EasyGetProduct(amount);
        product.setHeadFeeRate(productConf.getHeadFeeRate());
        product.setTailFeeRate(productConf.getTailFeeRate());
        product.setOverFeeRate(productConf.getOverdueFeeRate());
        product.setMinRepayAmount(productConf.getMinRepayAmount());

        return product;
    }

    @Override
    public LocalDateTime getRepaymentTime(Integer period) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime nowDate = LocalDateTime.of(nowTime.getYear(), nowTime.getMonth(), nowTime.getDayOfMonth(), 0, 0);
        return DateUtil.addDays(nowDate, period - 1);
    }

}