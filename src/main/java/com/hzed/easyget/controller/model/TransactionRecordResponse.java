package com.hzed.easyget.controller.model;

import lombok.Data;

import java.util.List;

/**
 * 返回交易记录
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class TransactionRecordResponse {
    private List<TransactionVO> list ;
}
