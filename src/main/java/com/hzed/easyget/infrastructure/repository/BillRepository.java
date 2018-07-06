package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.example.BillExample;
import com.hzed.easyget.persistence.auto.mapper.BillMapper;
import com.hzed.easyget.persistence.ext.entity.BillExt;
import com.hzed.easyget.persistence.ext.mapper.BillExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单表仓储层
 *
 * @author wuchengwu
 * @data 2018/6/5
 */
@Repository
public class BillRepository {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private BillExtMapper billExtMapper;

    public Bill findByBid(Long bid) {
        BillExample example = new BillExample();
        example.createCriteria().andBidIdEqualTo(bid);
        return billMapper.selectOneByExample(example);
    }

    public Bill findById(Long id) {
        return billMapper.selectByPrimaryKey(id);
    }

    public Bill findByIdWithExp(Long id) {
        Bill bill = findById(id);
        if (bill == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_BILLID, id);
        }
        return bill;
    }

    public List<Bill> findAllBillByBidId(Long bidId) {
        BillExample example = new BillExample();
        example.createCriteria().andBidIdEqualTo(bidId);
        return billMapper.selectByExample(example);
    }

    public List<Bill> findAllBillByBidIdWithExp(Long bidId) {
        List<Bill> list = findAllBillByBidId(bidId);
        if (list == null || list.isEmpty()) {
            throw new ComBizException(BizCodeEnum.NOT_EXIST_BILL, String.valueOf(bidId));
        }
        return list;
    }

    public void update(Bill bill) {
        billMapper.updateByPrimaryKeySelective(bill);
    }

    public List<BillExt> findUnRepayBillExt(){
        return billExtMapper.findUnRepayBillExt();
    }
}