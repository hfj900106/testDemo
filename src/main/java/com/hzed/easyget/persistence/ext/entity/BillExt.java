package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dengzhenhao
 * @since 2018/7/6 10:32
 */
@Data
public class BillExt {
    /**
     *  用户手机
     */
    private String mobile;

    /**
     *  账单id
     */
    private Long billId;

    /**
     *  应还时间
     */
    private LocalDateTime repaymentTime;
}
