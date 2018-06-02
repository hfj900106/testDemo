package com.hzed.easyget.application.service.product;

import com.hzed.easyget.application.service.product.model.BillInfo;
import com.hzed.easyget.persistence.auto.entity.Bid;

/**
 * @author guichang
 */
public interface ProductService {
    BillInfo createBills(Bid bid);
    void checkBid(Bid bid);
}
