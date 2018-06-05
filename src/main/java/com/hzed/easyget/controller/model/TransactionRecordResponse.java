package com.hzed.easyget.controller.model;

import com.hzed.easyget.persistence.auto.entity.TransactionRecord;
import lombok.Data;

import java.util.List;

/**
 * 返回教育记录
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class TransactionRecordResponse {
    private List<TransactionRecord> list ;
}
