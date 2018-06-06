package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.BillLedger;
import com.hzed.easyget.persistence.auto.entity.example.BillLedgerExample;
import com.hzed.easyget.persistence.auto.mapper.BillLedgerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单台账表
 *
 * @author wuchengwu
 * @data 2018/6/5
 */
@Repository
public class BillLedgerRepository {

    @Autowired
    private BillLedgerMapper loanBillLedgerMapper;

    public BillLedger findBillLedgerItemByBillId(Long billId, Byte item) {
        BillLedgerExample example = new BillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId).andRepaymentItemEqualTo(item);
        return loanBillLedgerMapper.selectOneByExample(example);
    }

    public BillLedger findBillLedgerItemByBillIdWithExp(Long billId, Byte item) {
        BillLedger billLedger = findBillLedgerItemByBillId(billId, item);
        if (billLedger == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + "的台账类型：" + item + "不存在");
        }
        return billLedger;
    }

    public List<BillLedger> findAllBillLedgerByBillId(Long billId) {
        BillLedgerExample example = new BillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId);
        return loanBillLedgerMapper.selectByExample(example);
    }

    public List<BillLedger> findAllBillLedgerByBillIdWithExp(Long billId) {
        List<BillLedger> list = findAllBillLedgerByBillId(billId);
        if (list == null || list.isEmpty()) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + "不存在台账");
        }
        return list;
    }
}