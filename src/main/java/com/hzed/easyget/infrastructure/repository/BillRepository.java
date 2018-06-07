package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.example.BillExample;
import com.hzed.easyget.persistence.auto.mapper.BillMapper;
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

    public Bill findRepayTimeByBid(Long bid) {
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
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + id + " 不存在");
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
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "标ID：" + bidId + " 的账单不存在");
        }
        return list;
    }

    public void update(Bill bill) {
        billMapper.updateByPrimaryKeySelective(bill);
    }
}