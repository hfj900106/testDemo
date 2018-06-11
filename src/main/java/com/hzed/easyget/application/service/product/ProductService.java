package com.hzed.easyget.application.service.product;

import com.hzed.easyget.application.service.product.model.BillInfo;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;

import java.util.List;

/**
 * @author guichang
 */
public interface ProductService {
    BillInfo createBillInfo(Bid bid);

    List<Bill> createBills(Bid bid);
    List<BillLedger> createBillLedger(Bill bill,Bid bid);
    List<BillLedger> createBillLedger(Bid bid);

    void checkBid(Bid bid);
}
